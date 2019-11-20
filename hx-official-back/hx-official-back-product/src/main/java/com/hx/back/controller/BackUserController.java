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
     * 登出
     * @return
     */
    @RequestMapping("/logout")
    public R logout(){
        ShiroUtils.logout();
        return R.ok();
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

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public R updateUser(@RequestBody HxUser user){
        backUserService.updateUser(user);
        return R.ok();
    }

    /**
     * 分页查询用户
     * @param user
     * @return
     */
    @PostMapping("/findUserByPage")
    public R findUserByPage(@RequestBody HxUser user){
        return backUserService.findUserByPage(user);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @PostMapping("/deleteUser")
    public R deleteUser(@RequestBody HxUser user){
        backUserService.deleteUser(user);
        return R.ok();
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @PostMapping("/batchDeleteUser")
    public R batchDeleteUser(@RequestBody int[] ids){
        backUserService.batchDeleteUser(ids);
        return R.ok();
    }


}
