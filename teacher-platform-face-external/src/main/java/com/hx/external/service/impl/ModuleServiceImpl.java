package com.hx.external.service.impl;

import com.hx.common.config.BootdoConfig;
import com.hx.external.domain.Module;
import com.hx.external.mapper.ModuleMapper;
import com.hx.external.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleMapper moduleDao;
    @Autowired
    BootdoConfig bootConfig;

    @Override
    public List<Module> listType(){
        List<Module> modules = moduleDao.listType();
        return modules;
    }


}
