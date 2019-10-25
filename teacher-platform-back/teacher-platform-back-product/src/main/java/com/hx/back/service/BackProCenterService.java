package com.hx.back.service;

import com.hx.back.entity.HxProCenter;
import com.hx.back.mapper.HxProCenterMapper;
import com.hx.common.exception.BDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackProCenterService {

    @Autowired
    private HxProCenterMapper hxProCenterMapper;

    public void addProCenter(HxProCenter hxProCenter) {
        int i = hxProCenterMapper.insertDynamic(hxProCenter);
        if (i != 1){
            throw new BDException("添加失败");
        }
    }


    public void updateProCenter(HxProCenter hxProCenter) {
        int i = hxProCenterMapper.updateDynamic(hxProCenter);
        if (i != 1){
            throw new BDException("修改失败");
        }
    }

    public void deleteProCenter(Integer id) {
        int i = hxProCenterMapper.delete(id);
        if (i != 1){
            throw new BDException("删除失败");
        }
    }

    public void deleteProCenters(int[] ids) {
        int i = hxProCenterMapper.deletes(ids);
        if (i < 1){
            throw new BDException("删除失败");
        }
    }
}
