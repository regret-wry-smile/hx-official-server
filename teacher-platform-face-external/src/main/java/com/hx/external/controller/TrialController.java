package com.hx.external.controller;

import com.hx.domain.R;
import com.hx.external.domain.TrialUsers;
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

    @PostMapping("/insertTrial")
    public R insertTrial(@RequestBody TrialUsers trialUsers){
        trialService.insterTrial(trialUsers);
        return R.ok();
    }

    @RequestMapping("/selectTrial")
    public R selectTrial(@RequestBody TrialUsers trialUsers){
        List<TrialUsers> trialUsersList = trialService.selectTrial(trialUsers);
        return R.ok(trialUsersList);
    }

    @PostMapping("/deleterial")
    public R deleteTrial(@RequestBody TrialUsers trialUsers){
        trialService.deleteTrial(trialUsers);
        return R.ok();
    }

    @PostMapping("/deleterials")
    public R deleteTrial(@RequestBody int[] ids){
        trialService.deleteTrials(ids);
        return R.ok();
    }

}
