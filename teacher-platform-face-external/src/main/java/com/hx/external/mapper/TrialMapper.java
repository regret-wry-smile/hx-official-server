package com.hx.external.mapper;

import com.hx.external.domain.TrialUsers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialMapper {

        int delete(Integer id);

        int insert(TrialUsers trialUsers);

        int insertDynamic(TrialUsers trialUsers);

        int updateDynamic(TrialUsers trialUsers);

        int update(TrialUsers trialUsers);

        TrialUsers selectById(Integer id);


}
