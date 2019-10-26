package com.hx.service;

import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.domain.HxUser;
import com.hx.mapper.SolutionCaseMapper;
import com.hx.pojo.SolutionCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SolutionService {
    @Autowired
    SolutionCaseMapper solutionMapper;
    public void saveSolution(SolutionCase solution) throws ParseException {
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(new Date());
        Date date = sf.parse(s);
        solution.setCreateTime(date);
        solution.setCteateUser(user.getUserName());
        solutionMapper.insertSelective(solution);
    }

    public void deleteSolution(List<Integer> asList) {
    }

    public void removeSolution(Integer id) {
    }

    public void updateSolution(SolutionCase solution) {
    }

    public Integer selectCount(Map<String, Object> params) {
        return null;
    }

    public Object listPage(Map<String, Object> map) {
        return solutionMapper.listPage(map);
    }

    public String queryOneNews(SolutionCase solution) {
        return null;
    }
}
