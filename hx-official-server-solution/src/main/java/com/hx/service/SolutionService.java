package com.hx.service;

import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.domain.HxUser;
import com.hx.mapper.SolutionCaseMapper;
import com.hx.mapper.SolutionCategoryMapper;
import com.hx.pojo.SolutionCase;
import com.hx.pojo.SolutionCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class SolutionService {
    @Autowired
    SolutionCaseMapper solutionMapper;
    @Autowired
    SolutionCategoryMapper solutionCategoryMapper;

    public void saveSolution(SolutionCase solution) throws ParseException {
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(new Date());
        Date date = sf.parse(s);
        solution.setCreateTime(date);
        solution.setCteateUser(user.getUserName());
        solutionMapper.insertSelective(solution);
    }

    public void deleteSolution(List<Integer> ids) {
        solutionMapper.deleteByIds(ids);
    }

    public void removeSolution(Integer id) {
        solutionMapper.deleteByPrimaryKey(id);
    }

    public void updateSolution(SolutionCase solution) throws ParseException {
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(new Date());
        Date date = sf.parse(s);
        solution.setUpdateTime(date);
        solution.setUpdateUser(user.getUserName());
        solutionMapper.updateByPrimaryKeySelective(solution);
    }

    public Integer selectCount(Map<String, Object> params) {
        return solutionMapper.selectCount(params);
    }

    public Object listPage(Map<String, Object> map) {
        return solutionMapper.listPage(map);
    }

    public SolutionCase queryOneSolution(SolutionCase solution) {
        return solutionMapper.selectByPrimaryKey(solution.getId());
    }

    public void saveSolutionCategory(SolutionCategory solutionCategory) {
        solutionCategoryMapper.insertSelective(solutionCategory);
    }

    public void deleteSolutionCategory(List<Integer> ids) {
        solutionCategoryMapper.deleteByIds(ids);
    }

    public void removeSolutionCategory(Integer id) {
        solutionCategoryMapper.deleteByPrimaryKey(id);
    }

    public void updateSolutionCategory(SolutionCategory solutionCategory) {
        solutionCategoryMapper.updateByPrimaryKeySelective(solutionCategory);
    }

    public Integer selectCountCategory(Map<String, Object> params) {
        return solutionCategoryMapper.selectCount(params);
    }

    public SolutionCategory queryOneSolutionCategory(SolutionCategory solutionCategory) {
        return  solutionCategoryMapper.selectByPrimaryKey(solutionCategory.getId());
    }

    public List<SolutionCategory> listPageCategory(Map<String, Object> params) {
        return solutionCategoryMapper.listPage(params);
    }
}
