package com.hx.system.shiro;

import com.hx.domain.UserDO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Component;


/**
 * shiroRealm
 */
@Component
public class UserRealm extends AuthorizingRealm {

    //获取授权信息 进行授权 （身份认证成功后从session中拿出用户信息进行授权）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = null;
        if (principals instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection collection = (SimplePrincipalCollection) principals;
            Object principal = collection.getPrimaryPrincipal();
            if (principal instanceof UserDO) {
                userId = ((UserDO) principal).getUserId();
            }
        }
//        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
//        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(perms);

        return info;
    }


    //获取认证信息 进行身份认证 认证通过后会把用户信息信息存到session中
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1、从token中获取username和password
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        //2、从数据库中查user对象  进行对比验证
//        UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
        // 查询用户信息
        UserDO user = null;
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号密码不正确");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    //初始化用户权限信息
//    public AuthorizationInfo initCurrentUserPermissions() {
//        return getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
//    }
    
}
