package com.hx.external.controller;

import com.hx.common.exception.BDException;
import com.hx.domain.R;
import com.hx.external.domain.TrialUsers;
import com.hx.external.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/SMS")
public class UtilController {

    @Autowired
    private UtilService utilService;

    //官网短信验证
    @RequestMapping("/sendSMS")
    public R sendSMS(@Valid @RequestBody TrialUsers trialUsers, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> errorList =  bindingResult.getAllErrors();
            for (ObjectError objectError : errorList){
                throw new BDException(objectError.getDefaultMessage());
            }

        }
        if (utilService.phoneVerify(trialUsers)){
            Integer code = utilService.sendSMS(trialUsers);
            return R.ok(code);
        }else {
            return R.ok();
        }
    }
}
