package com.hx.system.shiro;

import com.hx.domain.HxUser;
import com.hx.back.mapper.HxUserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * shiroRealm
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private HxUserMapper hxUserMapper;

    //获取授权信息 进行授权 （身份认证成功后从session中拿出用户信息进行授权）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer userId = null;
        if (principals instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection collection = (SimplePrincipalCollection) principals;
            Object principal = collection.getPrimaryPrincipal();
            if (principal instanceof HxUser) {
                userId = ((HxUser) principal).getId();
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
        //UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
        HxUser hxUser = new HxUser();
        hxUser.setUserName(username);
        hxUser.setUserPass(password);
        HxUser user = hxUserMapper.selectByUser(hxUser);
        if (user == null){
            throw new UnknownAccountException("用户名或密码错误");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    //初始化用户权限信息
//    public AuthorizationInfo initCurrentUserPermissions() {
//        return getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
//    }
    
}
