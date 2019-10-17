package com.hx.back.mapper;

import com.hx.back.entity.HxProTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HxProTreeMapper {

    int delete(Integer id);

    int insert(HxProTree hxProTree);

    int insertDynamic(HxProTree hxProTree);

    int updateDynamic(HxProTree hxProTree);

    int update(HxProTree hxProTree);

    HxProTree selectByCondition(HxProTree hxProTree);

    List<HxProTree> findWithResult(HxProTree hxProTreeDTO);

    List<HxProTree> findWithResultByPage(HxProTree hxProTreeDTO);

    Integer getMaxCode(HxProTree hxProTreeDTO);

    Integer findCount(HxProTree hxProTreeDTO);
}