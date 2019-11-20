package com.hx.mapper;

import com.hx.pojo.ProductWeb;
import com.hx.pojo.ProductWebExample;
import java.util.List;
import java.util.Map;

import com.hx.pojo.ProductWebVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ProductWebMapper {
    int countByExample(ProductWebExample example);

    int deleteByExample(ProductWebExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductWeb record);

    int insertSelective(ProductWeb record);

    List<ProductWeb> selectByExample(ProductWebExample example);

    ProductWeb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductWeb record, @Param("example") ProductWebExample example);

    int updateByExample(@Param("record") ProductWeb record, @Param("example") ProductWebExample example);

    int updateByPrimaryKeySelective(ProductWeb record);

    int updateByPrimaryKey(ProductWeb record);

    List<ProductWebVo> listPage(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    int deleteByIds(@Param("ids")List<Integer> ids);

}