package com.hx.mapper;

import com.hx.pojo.ProductWeb;
import com.hx.pojo.ProductwebCategory;
import com.hx.pojo.ProductwebCategoryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ProductwebCategoryMapper {
    int countByExample(ProductwebCategoryExample example);

    int deleteByExample(ProductwebCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductwebCategory record);

    int insertSelective(ProductwebCategory record);

    List<ProductwebCategory> selectByExample(ProductwebCategoryExample example);

    ProductwebCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductwebCategory record, @Param("example") ProductwebCategoryExample example);

    int updateByExample(@Param("record") ProductwebCategory record, @Param("example") ProductwebCategoryExample example);

    int updateByPrimaryKeySelective(ProductwebCategory record);

    int updateByPrimaryKey(ProductwebCategory record);

    List<ProductwebCategory> listPage(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    int deleteByIds(List<Integer> ids);

    Integer getMaxCode(ProductwebCategory productwebCategory);

}