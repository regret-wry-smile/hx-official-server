//package com.hx;
//
//import com.alibaba.fastjson.JSON;
//import com.hx.common.redis.shiro.RedisManager;
//import com.hx.common.redis.shiro.RedisSessionDAO;
//
//import com.hx.domain.UserDO;
//import com.hx.system.service.SessionService;
//import com.hx.system.shiro.UserRealm;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.Session;
//
//import org.apache.shiro.util.ThreadContext;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Collection;
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class HxPlatformWebApplicationTests {
//
///*    @Resource
//    org.apache.shiro.mgt.SecurityManager securityManager;
//
//    @Before
//    public void setUp() throws Exception {
//
//        ThreadContext.bind(securityManager);
//
//    }*/
//
//    /*@Autowired
//    HttpServletRequest request; //这里可以获取到request*/
//
//    @Autowired
//    SessionService sessionService;
//
//    @Autowired
//    RedisSessionDAO redisSessionDAO;
//
//    @Autowired
//    UserRealm userRealm;
//
//    @Autowired
//    RedisManager redisManager;
//
//    @Autowired
//    SecurityManager securityManager;
//
//
//    /*@Before
//    public void setUp() throws Exception {
//
//        ThreadContext.bind(securityManager);
//
//    }*/
//
//    @Test
//    public void contextLoads() {
////        UserDO user = ShiroUtils.getUser();
////        Subject subject = SecurityUtils.getSubject();
//
//        List<UserDO> userDOS = sessionService.listOnlineUser();
//        System.out.println("-------------"+userDOS);
//    }
//
//    @Test
//    public void getUser3(){
//
//        //获取所有在线用户session值
//        Collection<Session> sessions = sessionService.sessionList();
//        for (Session session : sessions) {
//            System.out.println("【"+session.getId()+"】");
//            for (Object o : session.getAttributeKeys()) {
//                Object value = session.getAttribute((String) o);
//                System.out.println((String) o+"  --值为--:  "+value);
//            }
//        }
//
//        /*Session session = redisSessionDAO.doReadSession("2efd2200-10b4-4b2c-908c-6e1186a662ba");
//        System.out.println("----------"+session.getAttributeKeys());*/
//    }
//
//    @Test
//    public void getUserPerms(){
//
//        AuthorizationInfo info1 = userRealm.getAuthorizationInfo1();
//        System.out.println("jjjjjjjjjjjjjj"+info1.getStringPermissions());
//    }
//
//
//
//}
//
