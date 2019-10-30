package com.hx.back.mapper;

import com.hx.back.entity.HxProCenter;
import com.hx.back.entity.HxProCenterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HxProCenterMapper {

    int delete(Integer id);

    int deletes(int[] array);

    int insertDynamic(HxProCenter hxProCenter);

    int updateDynamic(HxProCenter hxProCenter);

    HxProCenter selectById(HxProCenter hxProCenter);

    List<HxProCenter> selectByIds(int[] array);

    List<HxProCenterDTO> findPageWithResult(HxProCenterDTO hxProCenterDTO);

    Integer findPageWithCount(HxProCenterDTO hxProCenterDTO);

}