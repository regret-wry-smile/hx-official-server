package com.hx.common.exception;

import com.hx.common.config.Constant;
import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.common.service.LogService;
import com.hx.domain.HxUser;
import com.hx.domain.LogDO;
import com.hx.domain.R;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class BDExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LogService logService;

    /**
     * 自定义异常
     */
    @ExceptionHandler(BDException.class)
    public R handleBDException(BDException e) {
        logger.error(e.getMessage(), e);
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public R noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
        logger.error(e.getMessage(), e);
        return R.error(R.NOT_FOUND_RESOURCE_CODE, "没有找到资源");
    }

    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthenticationException(AuthenticationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
//        if (HttpServletUtils.jsAjax(request)) {
//            return R.error(403," 账号密码不正确或您权限不足");
//        }
//        return new ModelAndView("error/403");
        return R.error(R.NOT_LOGIN_CODE,e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
//        if (HttpServletUtils.jsAjax(request)) {
//            return R.error(403, "未授权");
//        }
//        return new ModelAndView("error/403");
        return R.error(R.UNAUTHORIZED_CODE, "未授权");
    }


    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        LogDO logDO = new LogDO();
        logDO.setGmtCreate(new Date());
        logDO.setOperation(Constant.LOG_ERROR);
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(e.toString());
        HxUser current = ShiroUtils.getUser();
        if(null!=current){
            logDO.setUserId(current.getId());
            logDO.setUsername(current.getUserName());
        }
        logService.save(logDO);
        logger.error(e.getMessage(), e);
//        if (HttpServletUtils.jsAjax(request)) {
//            return R.error(500, "服务器错误，请联系管理员");
//        }
//        return new ModelAndView("error/500");
        if (e instanceof BDException){
            return handleBDException((BDException)e);
        }
        return R.error(R.ERROR_CODE, "服务器错误，请联系管理员",e.getMessage());
    }
}
