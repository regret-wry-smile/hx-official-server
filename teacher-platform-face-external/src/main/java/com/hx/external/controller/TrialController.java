package com.hx.external.controller;

import com.hx.domain.R;
import com.hx.external.domain.TrialUsers;
import com.hx.external.domain.TrialUsersDTO;
import com.hx.external.service.TrialService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trial")
public class TrialController {

    @Autowired
    private TrialService trialService;

    //官网上传客户信息
    @PostMapping("/insertTrial")
    public R insertTrial(@RequestBody TrialUsers trialUsers){
        trialService.insterTrial(trialUsers);
        return R.ok();
    }

    //后台查找客户信息
    @RequestMapping("/selectTrial")
    public R selectTrial(@RequestBody TrialUsers trialUsers){
        List<TrialUsers> trialUsersList = trialService.selectTrial(trialUsers);
        return R.ok(trialUsersList);
    }

    //后台分页查找客户信息
    @RequestMapping("/selectByPage")
    public R selectByPage(@RequestBody TrialUsersDTO trialUsersDTO){
        List<TrialUsersDTO> trialUsersList = trialService.selectByPage(trialUsersDTO);
        int i = trialService.count(trialUsersDTO);
        return R.ok(trialUsersList,i);
    }

    //后台删除客户信息
    @PostMapping("/deleteTrial")
    public R deleteTrial(@RequestBody TrialUsers trialUsers){
        trialService.deleteTrial(trialUsers);
        return R.ok();
    }

    //后台批量删除客户信息
    @PostMapping("/deleteTrials")
    public R deleteTrials(@RequestBody int[] ids){
        trialService.deleteTrials(ids);
        return R.ok();
    }

}
