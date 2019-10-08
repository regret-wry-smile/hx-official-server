package com.hx.back.service;

import com.hx.common.utils.UUID;
import com.hx.domain.HxUser;
import com.hx.back.mapper.HxUserMapper;
import com.hx.common.exception.BDException;
import com.hx.common.utils.StringUtils;
import com.hx.domain.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackUserService {

    @Autowired
    private HxUserMapper hxUserMapper;

    public HxUser login(HxUser hxUser){

        if (StringUtils.isEmpty(hxUser.getUserName())){
            throw new BDException("用户名不能为空");
        }
        if (StringUtils.isEmpty(hxUser.getUserPass())){
            throw new BDException("密码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(hxUser.getUserName(), hxUser.getUserPass());
        Subject subject = SecurityUtils.getSubject();
        //身份认证、授权
        subject.login(token);

        return hxUser;
    }

    public void regist(HxUser user){

        if (StringUtils.isEmpty(user.getUserName())){
            throw new BDException("用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getUserPass())){
            throw new BDException("密码不能为空");
        }
        user.setUserId(UUID.get32UUID());
        HxUser hxUser = new HxUser();
        hxUser.setUserName(user.getUserName());
        hxUser = hxUserMapper.selectByUser(hxUser);
        if (hxUser != null){
            throw new BDException("用户名重复");
        }

        int i = hxUserMapper.insertDynamic(user);
        if (i != 1){
            throw new BDException("注册失败");
        }
    }

}
