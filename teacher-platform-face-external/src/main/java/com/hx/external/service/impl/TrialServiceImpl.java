package com.hx.external.service.impl;

import com.hx.common.exception.BDException;
import com.hx.common.utils.ListUtils;
import com.hx.common.utils.StringUtils;
import com.hx.external.api.HttpUtils;
import com.hx.external.domain.SMS;
import com.hx.external.domain.TrialUsersDTO;
import com.hx.external.mapper.TrialMapper;
import com.hx.external.domain.TrialUsers;
import com.hx.external.service.TrialService;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrialServiceImpl implements TrialService {

    @Autowired
    private TrialMapper trialMapper;

    @Resource(name = "phoneNumMap")
    private Map<String, SMS> phoneNumMap;

    @Override
    public boolean checkCode(TrialUsersDTO trialUsersDTO){
        SMS sms = phoneNumMap.get(trialUsersDTO.getPhone());
        if (!sms.getCode().equals(trialUsersDTO.getCode())){
            throw new BDException("验证码错误，请重新输入");
        }else {
            return true;
        }
    }

    @Override
    public void insterTrial(TrialUsers trialUsers){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateString = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        Date date1 = dateFormat.parse(dateString,pos);
        trialUsers.setDate(date1);
        int i  = trialMapper.insertDynamic(trialUsers);
        if (i != 1){
            throw new BDException("添加失败");
        }
    }

    @Override
    public List<TrialUsers> selectTrial(TrialUsers trialUsers){
        List<TrialUsers> trialUsersList = trialMapper.selectByTrial(trialUsers);
        if (ListUtils.isEmpty(trialUsersList)) {
            throw new BDException("查询失败");
        }
        return trialUsersList;
    }

    @Override
    public List<TrialUsersDTO> selectByPage(TrialUsersDTO trialUsersDTO){
        List<TrialUsersDTO> trialUsersList = trialMapper.list(trialUsersDTO);
        if (ListUtils.isEmpty(trialUsersList)) {
            throw new BDException("查询失败");
        }
        return trialUsersList;
    }

    @Override
    public int count(TrialUsersDTO trialUsersDTO){
        int i = trialMapper.count(trialUsersDTO);
        return i;
    }

    @Override
    public void deleteTrial(TrialUsers trialUsers){
        int i = trialMapper.deleteTrial(trialUsers);
        if (i != 1){
            throw new BDException("删除失败");
        }
    }

    @Override
    public void deleteTrials(int[] ids){
        int j = trialMapper.deleteByIds(ids);
        if (j < 1){
            throw new BDException("删除失败");
        }
    }

}
