package com.hx.back.service;

import com.hx.back.entity.HxProCenterTree;
import com.hx.back.entity.HxProTree;
import com.hx.back.mapper.HxProCenterTreeMapper;
import com.hx.common.exception.BDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackProCenterTreeService {

    @Autowired
    private HxProCenterTreeMapper hxProCenterTreeMapper;

    public void addProCenterTree(HxProCenterTree hxProCenterTree) {
        HxProCenterTree count = new HxProCenterTree();
        if (hxProCenterTree.getHardSoftType() == 1){
            count.setHardSoftType(1);
            int code = hxProCenterTreeMapper.getMaxCode(count);
            hxProCenterTree.setHardSoftCode(code+1);
        }
        if (hxProCenterTree.getHardSoftType() == 2){
            count.setHardSoftType(2);
            int code = hxProCenterTreeMapper.getMaxCode(count);
            hxProCenterTree.setHardSoftCode(code+1);
        }

        int i = hxProCenterTreeMapper.insertDynamic(hxProCenterTree);
        if (i != 1){
            throw new BDException("添加失败");
        }
    }


    public void updateProCenterTree(HxProCenterTree hxProCenterTree) {
        int i = hxProCenterTreeMapper.updateDynamic(hxProCenterTree);
        if (i != 1){
            throw new BDException("修改失败");
        }
    }

    public void deleteProCenterTree(int id) {
        int i = hxProCenterTreeMapper.delete(id);
        if (i != 1){
            throw new BDException("删除失败");
        }
    }

    public void deleteProCenterTrees(int[] ids) {
        int i = hxProCenterTreeMapper.deletes(ids);
        if (i != 1){
            throw new BDException("删除失败");
        }
    }
}
