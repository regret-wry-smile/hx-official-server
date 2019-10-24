package com.hx.external.service.impl;

import com.hx.common.config.BootdoConfig;
import com.hx.common.exception.BDException;
import com.hx.common.utils.ListUtils;
import com.hx.external.domain.Item;
import com.hx.external.domain.Module;
import com.hx.external.domain.ModuleDTO;
import com.hx.external.mapper.ModuleMapper;
import com.hx.external.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleMapper moduleMapper;
    @Autowired
    BootdoConfig bootConfig;

    @Override
    public List<Module> selectModule(Item item){
        Module module = new Module();
        module.setProjectName(item.getTitle());
        List<Module> modules = moduleMapper.selectModule(module);
        return modules;
    }

    @Override
    public List<Module> listType(){
        List<Module> modules = moduleMapper.listType();
        return modules;
    }

    @Override
    public List<ModuleDTO> selectByPage(ModuleDTO moduleDTO){
        List<ModuleDTO> moduleDTOS = moduleMapper.list(moduleDTO);
        if (ListUtils.isEmpty(moduleDTOS)) {
            throw new BDException("查询失败");
        }
        return moduleDTOS;
    }

    @Override
    public int count(ModuleDTO moduleDTO){
        int i = moduleMapper.count(moduleDTO);
        if (i == 0){
            throw new BDException("没有一条数据");
        }
        return i;
    }

    @Override
    public void insertModule(Module module){
        int i = moduleMapper.insertDynamic(module);
        if (i !=1 ){
            throw new BDException("添加失败");
        }
    }

    @Override
    public void deleteModule(Module module){
        int i = moduleMapper.deleteModule(module);
        if (i < 1 ){
            throw new BDException("删除失败");
        }
    }

    @Override
    public void deleteModules(int[] ids){
        int i = moduleMapper.deleteByIds(ids);
        if (i < 1 ){
            throw new BDException("删除失败");
        }
    }

    @Override
    public void updateModule(Module module){
        int i = moduleMapper.update(module);
        if (i != 1 ){
            throw new BDException("修改失败");
        }
    }
}
