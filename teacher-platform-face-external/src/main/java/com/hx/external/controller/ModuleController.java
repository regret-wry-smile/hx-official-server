package com.hx.external.controller;


import com.hx.external.service.ExternalService;
import com.hx.external.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("Module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    private ExternalService service;

    @PostMapping("/SelectModule")
    public HashMap selectModule(){
        List<String> projectTypes = moduleService.listType();
        HashMap type = service.SelectExternal(projectTypes);
        return type;
    }

}
