package com.hx.external.controller;


import com.hx.domain.R;
import com.hx.external.domain.External;
import com.hx.external.domain.ExternalDTO;
import com.hx.external.domain.Item;
import com.hx.external.domain.Module;
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

    @PostMapping("/selectModule")
    public R selectModule(){
        List<Module> modules = moduleService.listType();
        List<Item> itemList = externalService.SelectExternal(modules);
        return R.ok(itemList);
    }

    @RequestMapping("selectModules")
    public R selectModules(){
        List<Module> modules = moduleService.listType();
        return R.ok(modules);
    }

    @RequestMapping("/selectByPage")
    public  R selectByPage(@RequestBody ExternalDTO externalDTO){
        List<ExternalDTO> externalDTOS = externalService.selectByPage(externalDTO);
        int i = externalService.count(externalDTO);
        return R.ok(externalDTOS,i);
    }

    @RequestMapping("/deleteModule")
    public R deleteModule(@RequestBody External external){
        externalService.deleteModule(external);
        return R.ok();
    }

    @RequestMapping("/deleteModules")
    public R deleteModules(@RequestBody int[] ids){
        externalService.deleteModules(ids);
        return R.ok();
    }

    @RequestMapping("/insertModule")
    public R insertModule(@RequestBody External external){
        externalService.InsertExternal(external);
        return R.ok();
    }

    @RequestMapping("/updateModule")
    public R updateModule(@RequestBody External external){
        externalService.updateModule(external);
        return R.ok();
    }

}
