package com.hx.external.controller;


import com.hx.domain.R;
import com.hx.external.domain.Item;
import com.hx.external.domain.Module;
import com.hx.external.service.ExternalService;
import com.hx.external.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ExternalService service;

    @PostMapping("/selectModule")
    public R selectModule(){
        List<Module> modules = moduleService.listType();
        List<Item> itemList = service.SelectExternal(modules);
        return R.ok(itemList);
    }

}
