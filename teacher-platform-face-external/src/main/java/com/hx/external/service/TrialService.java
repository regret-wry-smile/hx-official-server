package com.hx.external.service;

import com.hx.external.domain.TrialUsers;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TrialService {

     void insterTrial(TrialUsers trialUsers);

    List<TrialUsers> selectTrial(TrialUsers trialUsers);

    void deleteTrial(TrialUsers trialUsers);

    void deleteTrials(int[] ids);
}
