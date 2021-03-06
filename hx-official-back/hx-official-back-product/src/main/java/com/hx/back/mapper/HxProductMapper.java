package com.hx.back.mapper;

import com.hx.back.entity.HxProduct;
import com.hx.back.entity.HxProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HxProductMapper {

    int delete(Integer id);

    int bantchDelete(int[] array);

    List<HxProduct> selectByIds(int[] array);

    int insert(HxProduct hxProduct);

    int insertDynamic(HxProduct hxProduct);

    int updateDynamic(HxProduct hxProduct);

    int update(HxProduct hxProduct);

    HxProductDTO selectById(HxProduct hxProduct);

    List<HxProduct> findResultByName(HxProduct hxProduct);

    List<HxProductDTO> selectAllByPage(@Param("hxProductDTO") HxProductDTO hxProductDTO);

    Integer findPageWithCount(HxProduct hxProduct);

    Integer findAllCount();
}
