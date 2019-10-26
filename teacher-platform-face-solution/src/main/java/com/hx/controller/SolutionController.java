package com.hx.controller;

import com.hx.domain.R;
import com.hx.pojo.SolutionCase;
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
    public R queryNews(@RequestBody(required = false) Map<String, Object> params){
        Integer count = solutionService.selectCount(params);
        return R.ok(solutionService.listPage(params),count);
    }
    @RequestMapping("/query")
    public R queryOneNews( @RequestBody SolutionCase solution){
        return R.ok(solutionService.queryOneNews(solution));
    }
}
