package com.hx.mapper;

import com.hx.pojo.SolutionCase;
import com.hx.pojo.SolutionCaseExample;
import java.util.List;
import java.util.Map;

import com.hx.pojo.SolutionCaseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SolutionCaseMapper {
    int countByExample(SolutionCaseExample example);

    int deleteByExample(SolutionCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SolutionCase record);

    int insertSelective(SolutionCase record);

    List<SolutionCase> selectByExample(SolutionCaseExample example);

    SolutionCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SolutionCase record, @Param("example") SolutionCaseExample example);

    int updateByExample(@Param("record") SolutionCase record, @Param("example") SolutionCaseExample example);

    int updateByPrimaryKeySelective(SolutionCase record);

    List<SolutionCaseVO> listPage(Map<String, Object> map);

    int deleteByIds(@Param("ids")List<Integer> ids);

    Integer selectCount(Map<String, Object> map);
}