package com.hx.external.service.impl;

import com.hx.external.mapper.TrialMapper;
import com.hx.external.domain.TrialUsers;
import com.hx.external.service.TrialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrialServiceImpl implements TrialService {

    @Autowired
    private TrialMapper trialMapper;

    @Override
    public int insterTrial(TrialUsers trialUsers){

         return trialMapper.insertDynamic(trialUsers);
    }
}
