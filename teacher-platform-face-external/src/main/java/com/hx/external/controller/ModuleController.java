package com.hx.external.controller;


import com.hx.domain.R;
import com.hx.external.domain.*;
import com.hx.external.service.ExternalService;
import com.hx.external.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ExternalService externalService;

    //官网展示所有API文档信息
    @PostMapping("/selectModule")
    public R selectModule(Item item){
        List<Module> modules = moduleService.selectModule(item);
        List<Item> itemList = externalService.SelectExternal(modules);
        return R.ok(itemList);
    }

    //后台展示所有项目
    @RequestMapping("/listType")
    public R listType(){
        List<Module> modules = moduleService.listType();
        return R.ok(modules);
    }

    //后台分页查询项目
    @RequestMapping("/selectByPage")
    public  R selectByPage(@RequestBody ModuleDTO moduleDTO){
        List<ModuleDTO> moduleDTOS = moduleService.selectByPage(moduleDTO);
        int i = moduleService.count(moduleDTO);
        return R.ok(moduleDTOS,i);
    }

    //后台添加项目
    @RequestMapping("/insertModule")
    public R insertModule(@RequestBody Module module){
        moduleService.insertModule(module);
        return R.ok();
    }

    //后台删除项目
    @RequestMapping("/deleteModule")
    public R deleteModule(@RequestBody Module module){
        moduleService.deleteModule(module);
        return R.ok();
    }

    //后台批量删除项目
    @RequestMapping("/deleteModules")
    public R deleteModules(@RequestBody int[] ids){
        moduleService.deleteModules(ids);
        return R.ok();
    }

    //后台修改项目
    @RequestMapping("/updateModule")
    public R updateModule(@RequestBody Module module){
        moduleService.updateModule(module);
        return R.ok();
    }
}
