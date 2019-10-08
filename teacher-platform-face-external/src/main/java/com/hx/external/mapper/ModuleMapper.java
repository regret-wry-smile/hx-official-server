package com.hx.external.mapper;

import com.hx.external.domain.Module;

public interface ModuleMapper {
    int delete(Integer id);

    int insert(Module module);

    int insertDynamic(Module module);

    int updateDynamic(Module module);

    int update(Module module);

    Module selectById(Integer id);

    Module selectProjectType();

}
