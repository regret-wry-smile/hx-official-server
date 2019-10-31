package com.hx.external.controller;

import com.hx.domain.R;
import com.hx.external.domain.TrialUsers;
import com.hx.external.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("SMS")
public class UtilController {

    @Autowired
    private UtilService utilService;

    //官网发邮件
    @RequestMapping("sendSMS")
    public R sendSMS(@RequestBody TrialUsers trialUsers){
        Integer code = utilService.sendSMS(trialUsers);
        return R.ok(code);
    }
}
