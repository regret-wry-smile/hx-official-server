package com.hx.back.mapper;

import com.hx.back.entity.HxProCenterTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HxProCenterTreeMapper {

    int delete(Integer id);

    int deletes(int[] ids);

    int insert(HxProCenterTree hxProCenterTree);

    int insertDynamic(HxProCenterTree hxProCenterTree);

    int updateDynamic(HxProCenterTree hxProCenterTree);

    int update(HxProCenterTree hxProCenterTree);

    HxProCenterTree selectById(Integer id);

    List<HxProCenterTree> findPageWithResult(HxProCenterTree hxProCenterTreeDTO);

    Integer findPageWithCount(HxProCenterTree hxProCenterTreeDTO);

    int getMaxCode(HxProCenterTree count);

}