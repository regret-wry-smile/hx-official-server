package com.hx.external.service.impl;

import com.hx.common.config.BootdoConfig;
import com.hx.domain.R;
import com.hx.external.domain.Module;
import com.hx.external.mapper.ModuleMapper;
import com.hx.external.service.ModuleService;
import org.apache.xerces.xs.StringList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleMapper moduleDao;
    @Autowired
    BootdoConfig bootConfig;

    @Override
    public List<String> listType(){
        List<Module> modules = moduleDao.listType();
        List<String> projectTypes = null;
        for (int i = 0; i <modules.size() ; i++) {
            String projectType = modules.get(i).getProjectType();
            projectTypes.add(projectType);
        }
        return projectTypes;
    }

}
