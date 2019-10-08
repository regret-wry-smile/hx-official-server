package com.hx.external.mapper;

import com.hx.external.domain.External;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExternalMapper {

        int delete(Integer id);

        int insert(External external);

        int insertDynamic(External external);

        int updateDynamic(External external);

        int update(External external);

        External selectById(Integer id);

        List<External> selectByType(String projectType);
}
