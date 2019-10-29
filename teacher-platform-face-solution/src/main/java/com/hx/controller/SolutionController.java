package com.hx.controller;

import com.hx.domain.R;
import com.hx.pojo.SolutionCase;
import com.hx.pojo.SolutionCategory;
import com.hx.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/solution")
public class SolutionController {
    @Autowired
    SolutionService solutionService;
    @RequestMapping("/query_by_page")
    public R querySolution(@RequestBody(required = false) Map<String, Object> params){
        Integer count = solutionService.selectCount(params);
        return R.ok(solutionService.listPage(params),count);
    }
    @RequestMapping("/query")
    public R queryOneSolution( @RequestBody SolutionCase solution){
        return R.ok(solutionService.queryOneSolution(solution));
    }

    @RequestMapping("/query_by_page_category")
    public R querySolutionCategory(@RequestBody(required = false) Map<String, Object> params){
        Integer count = solutionService.selectCountCategory(params);
        return R.ok(solutionService.listPageCategory(params),count);
    }
    @RequestMapping("/query_category")
    public R queryOneSolutionCategory( @RequestBody SolutionCategory solutionCategory){
        return R.ok(solutionService.queryOneSolutionCategory(solutionCategory));
    }
}
