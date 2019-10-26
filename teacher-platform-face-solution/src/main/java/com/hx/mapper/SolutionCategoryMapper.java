package com.hx.mapper;

import com.hx.pojo.SolutionCategory;
import com.hx.pojo.SolutionCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SolutionCategoryMapper {
    int countByExample(SolutionCategoryExample example);

    int deleteByExample(SolutionCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SolutionCategory record);

    int insertSelective(SolutionCategory record);

    List<SolutionCategory> selectByExample(SolutionCategoryExample example);

    SolutionCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SolutionCategory record, @Param("example") SolutionCategoryExample example);

    int updateByExample(@Param("record") SolutionCategory record, @Param("example") SolutionCategoryExample example);

    int updateByPrimaryKeySelective(SolutionCategory record);

    int updateByPrimaryKey(SolutionCategory record);
}