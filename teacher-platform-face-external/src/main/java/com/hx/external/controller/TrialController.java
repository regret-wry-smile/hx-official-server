package com.hx.external.controller;

import com.hx.common.exception.BDException;
import com.hx.domain.R;
import com.hx.external.domain.TrialUsers;
import com.hx.external.domain.TrialUsersDTO;
import com.hx.external.service.MailService;
import com.hx.external.service.TrialService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("trial")
public class TrialController {

    @Autowired
    private TrialService trialService;
    @Autowired
    private MailService mailService;

    //官网上传客户信息
    @PostMapping("/insertTrial")
    public R insertTrial(@Valid @RequestBody TrialUsersDTO trialUsers, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> errorList =  bindingResult.getAllErrors();
            for (ObjectError objectError : errorList){
                throw new BDException(objectError.getDefaultMessage());
            }
        }
        if (trialService.checkCode(trialUsers)){
            trialService.insterTrial(trialUsers);
            String content = mailService.testMail(trialUsers);
            mailService.sendSimpleMail("yangkang@huixiangtech.cn","申请试用客户信息",content);
        }
        return R.ok();
    }


    //官网发送邮件
    @RequestMapping("/mail")
    public R mail(@RequestBody TrialUsers trialUsers){
        mailService.sendSimpleMail(trialUsers.getEmail(),"test simple mail"," hello this is simple mail");
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
