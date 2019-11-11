package com.hx.external.service;

import com.hx.external.domain.TrialUsers;
import com.hx.external.domain.TrialUsersDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TrialService {

    boolean checkCode(TrialUsersDTO trialUsersDTO);

    void insterTrial(TrialUsers trialUsers);

    List<TrialUsers> selectTrial(TrialUsers trialUsers);

    List<TrialUsersDTO> selectByPage(TrialUsersDTO trialUsersDTO);

    int count(TrialUsersDTO trialUsersDTO);

    void deleteTrial(TrialUsers trialUsers);

    void deleteTrials(int[] ids);

}
