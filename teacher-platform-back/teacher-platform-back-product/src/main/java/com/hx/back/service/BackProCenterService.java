package com.hx.back.service;

import com.hx.back.entity.HxProCenter;
import com.hx.back.mapper.HxProCenterMapper;
import com.hx.common.exception.BDException;
import com.hx.common.fastdfs.FastfdsClient;
import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.common.utils.StringUtils;
import com.hx.common.utils.UUID;
import com.hx.domain.HxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BackProCenterService {

    @Autowired
    private HxProCenterMapper hxProCenterMapper;
    @Autowired
    private FastfdsClient fastfdsClient;

    public void addProCenter(HxProCenter hxProCenter) {
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hxProCenter.setCreateTime(sf.format(new Date()));
        hxProCenter.setProCenterId(UUID.get32UUID());
        hxProCenter.setCreateUser(user.getUserName());

        int i = hxProCenterMapper.insertDynamic(hxProCenter);
        if (i != 1){
            throw new BDException("添加产品失败");
        }
    }


    public void updateProCenter(HxProCenter hxProCenter) {
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hxProCenter.setUpdateTime(sf.format(new Date()));
        hxProCenter.setUpdateUser(user.getUserName());

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
        if (!StringUtils.isEmpty(hxPro.getProDetilImgs())){
            String[] imgs = hxPro.getProDetilImgs().split("#");
            for (String img : imgs){
                fastfdsClient.deleteFile(img);
            }
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
            if (!StringUtils.isEmpty(hxProCenter.getProDetilImgs())){
                String[] imgs = hxProCenter.getProDetilImgs().split("#");
                for (String img : imgs){
                    fastfdsClient.deleteFile(img);
                }
            }
        }
        int i = hxProCenterMapper.deletes(ids);
        if (i < 1){
            throw new BDException("删除产品失败");
        }
    }
}
