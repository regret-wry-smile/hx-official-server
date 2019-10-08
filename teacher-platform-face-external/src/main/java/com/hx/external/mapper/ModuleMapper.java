package com.hx.external.mapper;

import com.hx.domain.DictDO;
import com.hx.external.domain.Module;

import java.util.List;

public interface ModuleMapper {
    int delete(Integer id);

    int insert(Module module);

    int insertDynamic(Module module);

    int updateDynamic(Module module);

    int update(Module module);

    Module selectById(Integer id);

    List<Module> listType();

}
