package com.hx.common.aspect;

import com.hx.common.annotation.Log;
import com.hx.common.annotation.Type;
import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.common.service.LogService;
import com.hx.common.utils.HttpContextUtils;
import com.hx.common.utils.IPUtils;
import com.hx.common.utils.JSONUtils;
import com.hx.common.utils.UserAgentUtils;
import com.hx.domain.HxUser;
import com.hx.domain.LogDO;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志切面
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    LogService logService;


    //切入点定义（切入到@Log注解上）
    @Pointcut("@annotation(com.hx.common.annotation.Log)")
    public void logPointCut() {
    }

    //环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
    @Around("logPointCut()")  //目标方法为：logPointCut()
    public Object around(ProceedingJoinPoint point) throws Throwable {

        String methodName = point.getSignature().getName();
        HxUser userDO = null;
        if (methodName.trim().equals("logout")){ //页面调用该方法时会退出当前登陆用户, 导致在sql更新日志时获取不到当前登陆人, 所以此处提前将当前登陆人取出来
            userDO = ShiroUtils.getUser();
        }
        long beginTime = System.currentTimeMillis();
//        System.out.println("【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(point.getArgs()));
        // 执行目标方法
        Object result = point.proceed();  //执行目标方法
//        System.out.println("【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with " + result);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        if (userDO != null){ //不为空  表示是调的登出方法 ,为了保证在更新Sql日志时能获取到, 需要创建一个临时登陆用户,线程退出后会自动删除
            ThreadContext.bind(new DefaultSecurityManager());
            ThreadContext.bind(new Subject.Builder().principals(new SimplePrincipalCollection(userDO, "userDO")).buildSubject());
        }
        saveLog(point, time);
//        System.out.println("【环绕通知中的--->后置通知】：-----------------end.----------------------");
        return result;
    }

    /**
     *写入日志记录
     * @param joinPoint
     * @param time
     * @throws InterruptedException
     * AspectJ使用org.aspectj.lang.JoinPoint接口表示目标类连接点对象，如果是环绕增强时，使用org.aspectj.lang.ProceedingJoinPoint表示连接点对象，该类是JoinPoint的子接口。任何一个增强方法都可以通过将第一个入参声明为JoinPoint访问到连接点上下文的信息。我们先来了解一下这两个接口的主要方法： 
     * 1)JoinPoint 
     *  java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表； 
     *  Signature getSignature() ：获取连接点的方法签名对象； 
     *  java.lang.Object getTarget() ：获取连接点所在的目标对象； 
     *  java.lang.Object getThis() ：获取代理对象本身； 
     * 2)ProceedingJoinPoint 
     * ProceedingJoinPoint继承JoinPoint子接口，它新增了两个用于执行连接点方法的方法： 
     *  java.lang.Object proceed() throws java.lang.Throwable：通过反射执行目标对象的连接点处的方法； 
     *  java.lang.Object proceed(java.lang.Object[] args) throws java.lang.Throwable：通过反射执行目标对象连接点处的方法，不过使用新的入参替换原来的入参。 
     * ---------------------
     *
     */
    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        LogDO logDO = new LogDO();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getName().startsWith("update")){
            logDO.setType(Type.U.name());
        }else if(method.getName().startsWith("del")|| method.getName().startsWith("remove")){
            logDO.setType(Type.D.name());
        }else if(method.getName().startsWith("add")||method.getName().startsWith("save")||method.getName().startsWith("insert")){
            logDO.setType(Type.C.name());
        } else {
            logDO.setType(Type.R.name());
        }
        Log log = method.getAnnotation(Log.class);
        if (log.value() != null) {
            // 注解上的描述
            logDO.setOperation(log.value());
        }
        if (log.module() != null) {
            // 注解上的所属模块
            logDO.setTableName(log.module().name());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logDO.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            logDO.setParams(params);
        } catch (Exception e) {

        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        logDO.setIp(IPUtils.getIpAddr(request));
        // 用户名
        HxUser currUser = ShiroUtils.getUser();
        if (null == currUser) {
            if (null != logDO.getParams()) {
                logDO.setUserId(-1);
                logDO.setUsername(logDO.getParams());
            } else {
                logDO.setUserId(-1);
                logDO.setUsername("获取用户信息为空");
            }
        } else {
            logDO.setUserId(ShiroUtils.getUserId());
            logDO.setUsername(ShiroUtils.getUser().getUserName());
        }
        logDO.setTime((int) time);
        // 系统当前时间
        Date date = new Date();
        logDO.setGmtCreate(date);
        logDO.setHttp(request.getRequestURI());
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("Java")||userAgent.contains("java")){
            logDO.setSys(userAgent);
        }else {
            String browserName = UserAgentUtils.getBrowserName(userAgent);
            String browserVersion = UserAgentUtils.getBrowserVersion(userAgent);
            String osName = UserAgentUtils.getOsName(userAgent);
            String osVersion = UserAgentUtils.getOsVersion(userAgent);
            logDO.setSys(osName + " " + osVersion + " ; " + browserName + " " + browserVersion);
        }
        // 保存系统日志
        logService.save(logDO);
    }
}
