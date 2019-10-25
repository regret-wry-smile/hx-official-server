package com.hx.back.service;

import com.hx.back.entity.HxProCenter;
import com.hx.back.mapper.HxProCenterMapper;
import com.hx.common.exception.BDException;
import com.hx.common.fastdfs.FastfdsClient;
import com.hx.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackProCenterService {

    @Autowired
    private HxProCenterMapper hxProCenterMapper;
    @Autowired
    private FastfdsClient fastfdsClient;

    public void addProCenter(HxProCenter hxProCenter) {
        int i = hxProCenterMapper.insertDynamic(hxProCenter);
        if (i != 1){
            throw new BDException("添加产品失败");
        }
    }


    public void updateProCenter(HxProCenter hxProCenter) {
        int i = hxProCenterMapper.updateDynamic(hxProCenter);
        if (i != 1){
            throw new BDException("修改产品失败");
        }
    }

    public void deleteProCenter(HxProCenter hxProCenter) {
        HxProCenter hxPro = hxProCenterMapper.selectById(hxProCenter);
        if (!StringUtils.isEmpty(hxPro.getProImg())){
            fastfdsClient.deleteFile(hxPro.getProImg());
        }
        int i = hxProCenterMapper.delete(hxProCenter.getId());
        if (i != 1){
            throw new BDException("删除产品失败");
        }
    }

    public void deleteProCenters(int[] ids) {
        List<HxProCenter> hxProCenters = hxProCenterMapper.selectByIds(ids);
        for (HxProCenter hxProCenter : hxProCenters){
            if (!StringUtils.isEmpty(hxProCenter.getProImg())){
                fastfdsClient.deleteFile(hxProCenter.getProImg());
            }
        }
        int i = hxProCenterMapper.deletes(ids);
        if (i < 1){
            throw new BDException("删除产品失败");
        }
    }
}
