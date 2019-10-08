package com.hx.back.controller;

import com.hx.domain.HxUser;
import com.hx.back.service.BackUserService;
import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class BackUserController {

    @Autowired
    private BackUserService backUserService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public R login(@RequestBody HxUser user){
        return R.ok(backUserService.login(user));
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/regist")
    public R regist(@RequestBody HxUser user){
        backUserService.regist(user);
        return R.ok();
    }


}
