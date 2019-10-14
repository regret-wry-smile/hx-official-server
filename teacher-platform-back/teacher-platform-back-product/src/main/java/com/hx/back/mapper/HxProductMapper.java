package com.hx.back.mapper;

import com.hx.back.entity.HxProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HxProductMapper {

    int delete(Integer id);

    int insert(HxProduct hxProduct);

    int insertDynamic(HxProduct hxProduct);

    int updateDynamic(HxProduct hxProduct);

    int update(HxProduct hxProduct);

    HxProduct selectById(String id);

    List<HxProduct> findResultLimit(HxProduct hxProduct);

    List<HxProduct> findAllResult(HxProduct hxProduct);

    Integer findPageWithCount(HxProduct hxProduct);
}
