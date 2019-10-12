package com.hx.back.service;

import com.hx.common.utils.UUID;
import com.hx.domain.HxUser;
import com.hx.back.mapper.HxUserMapper;
import com.hx.common.exception.BDException;
import com.hx.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class BackUserService {

    @Autowired
    private HxUserMapper hxUserMapper;

    public HxUser login(HxUser hxUser){

        hxUser.setUserPass(encryptToMD5(hxUser.getUserPass()));

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
        user.setUserPass(encryptToMD5(user.getUserPass()));
        int i = hxUserMapper.insertDynamic(user);
        if (i != 1){
            throw new BDException("注册失败");
        }
    }

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String encryptToMD5(String str){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


}

