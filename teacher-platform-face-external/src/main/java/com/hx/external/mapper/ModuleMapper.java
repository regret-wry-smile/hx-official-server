package com.hx.external.mapper;

import com.hx.external.domain.Module;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ModuleMapper {

    int delete(Integer id);

    int insert(Module module);

    int insertDynamic(Module module);

    int updateDynamic(Module module);

    int update(Module module);

    Module selectById(Integer id);

    List<Module> listType();

    Module selectByModule(Module module);

}
