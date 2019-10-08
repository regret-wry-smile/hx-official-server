package com.hx.external.controller;

import com.hx.domain.R;
import com.hx.external.domain.TrialUsers;
import com.hx.external.service.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trial")
public class TrialController {

    @Autowired
    private TrialService trialService;

    @PostMapping("/insertTrial")
    public R insertTrial(@RequestBody TrialUsers trialUsers){
        return R.ok(trialService.insterTrial(trialUsers));
    }


}
