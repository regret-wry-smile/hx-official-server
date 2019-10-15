package com.hx.back.mapper;

import com.hx.back.entity.HxProduct;
import com.hx.back.entity.HxProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HxProductMapper {

    int delete(Integer id);

    int insert(HxProduct hxProduct);

    int insertDynamic(HxProduct hxProduct);

    int updateDynamic(HxProduct hxProduct);

    int update(HxProduct hxProduct);

    HxProductDTO selectById(String id);

    List<HxProduct> findResultByName(HxProduct hxProduct);

    List<HxProduct> selectAllByPage(Map<String,Object> object);

    Integer findPageWithCount(HxProduct hxProduct);
}
