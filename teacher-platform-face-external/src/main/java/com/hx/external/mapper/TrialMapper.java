package com.hx.external.mapper;

import com.hx.external.domain.TrialUsers;
import com.hx.external.domain.TrialUsersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrialMapper {

        int delete(Integer id);

        int deleteTrial(TrialUsers trialUsers);

        int deleteByIds(int[] ids);

        int insert(TrialUsers trialUsers);

        int insertDynamic(TrialUsers trialUsers);

        int updateDynamic(TrialUsers trialUsers);

        int update(TrialUsers trialUsers);

        TrialUsers selectById(Integer id);

        List<TrialUsers> selectByTrial(TrialUsers trialUsers);

        List<TrialUsersDTO> list(TrialUsersDTO TrialUsers);

        int count(TrialUsersDTO TrialUsers);

}