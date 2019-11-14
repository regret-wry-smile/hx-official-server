package com.hx.controller;

import com.hx.domain.R;
import com.hx.pojo.SolutionCase;
import com.hx.pojo.SolutionCategory;
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
    public R saveSolution(@RequestBody SolutionCase solution) throws ParseException {
        solutionService.saveSolution(solution);
        return R.ok();
    }

    @RequestMapping("/batchremove")
    public R deleteSolution(@RequestBody Integer[]  ids){
        solutionService.deleteSolution(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/remove")
    public R removeSolution(@RequestBody SolutionCase solution){
        solutionService.removeSolution(solution.getId());
        return R.ok();
    }

    @RequestMapping("/update")
    public R updateSolutionCategory(@RequestBody SolutionCase solution) throws ParseException {
        solutionService.updateSolution(solution);
        return R.ok();
    }

    @RequestMapping("/addCategory")
    public R saveSolutionCategory(@RequestBody SolutionCategory solutionCategory) throws ParseException {
        solutionService.saveSolutionCategory(solutionCategory);
        return R.ok();
    }

    @RequestMapping("/batchremoveCategory")
    public R deleteSolutionCategory(@RequestBody Integer[]  ids){
        solutionService.deleteSolutionCategory(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/removeCategory")
    public R removeSolutionCategory(@RequestBody SolutionCategory solutionCategory){
        solutionService.removeSolutionCategory(solutionCategory.getId());
        return R.ok();
    }

    @RequestMapping("/updateCategory")
    public R updateSolution(@RequestBody SolutionCategory solutionCategory) throws ParseException {
        solutionService.updateSolutionCategory(solutionCategory);
        return R.ok();
    }
}
