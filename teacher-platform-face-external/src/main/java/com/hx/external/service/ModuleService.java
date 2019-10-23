package com.hx.external.service;

import com.hx.external.domain.Module;
import com.hx.external.domain.ModuleDTO;

import java.util.List;

public interface ModuleService {

    List<Module> listType();

    List<ModuleDTO> selectByPage(ModuleDTO moduleDTO);

    int count(ModuleDTO moduleDTO);

    void insertModule(Module module);

    void deleteModule(Module module);

    void deleteModules(int[] ids);

    void updateModule(Module module);
}
