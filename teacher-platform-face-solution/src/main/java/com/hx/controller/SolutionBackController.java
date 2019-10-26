package com.hx.controller;

import com.hx.domain.R;
import com.hx.pojo.SolutionCase;
import com.hx.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Arrays;

@RestController
@RequestMapping("/backsolution")
public class SolutionBackController {
    @Autowired
    SolutionService solutionService;
    @RequestMapping("/add")
    public R saveNews(@RequestBody SolutionCase solution) throws ParseException {
        solutionService.saveSolution(solution);
        return R.ok();
    }

    @RequestMapping("/batchremove")
    public R deleteNews(@RequestBody Integer[]  ids){
        solutionService.deleteSolution(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/remove")
    public R removeNews(@RequestBody SolutionCase solution){
        solutionService.removeSolution(solution.getId());
        return R.ok();
    }

    @RequestMapping("/update")
    public R updateNews(@RequestBody SolutionCase solution) throws ParseException {
        solutionService.updateSolution(solution);
        return R.ok();
    }
}
