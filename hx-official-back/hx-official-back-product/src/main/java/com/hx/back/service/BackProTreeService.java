package com.hx.back.service;

import com.hx.back.entity.HxProTree;
import com.hx.back.mapper.HxProTreeMapper;
import com.hx.common.exception.BDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackProTreeService {

    @Autowired
    private HxProTreeMapper hxProTreeMapper;

    public void insertProTree(HxProTree hxProTree){
        HxProTree count = new HxProTree();
        if (hxProTree.getConditionId() == 1){
            count.setConditionId(1);
            int code = hxProTreeMapper.getMaxCode(count);
            hxProTree.setCode(code+1);
            hxProTree.setConditionName("按产品");
        }
        if (hxProTree.getConditionId() == 2){
            count.setConditionId(1);
            int code = hxProTreeMapper.getMaxCode(count);
            hxProTree.setCode(code+1);
            hxProTree.setConditionName("按行业");
        }

        int i = hxProTreeMapper.insert(hxProTree);
        if (i !=1){
            throw new BDException("添加失败");
        }

    }

    public void updateProTree(HxProTree hxProTree){
        int i = hxProTreeMapper.update(hxProTree);
        if (i !=1){
            throw new BDException("修改失败");
        }
    }

    public void deleteProTree(HxProTree hxProTree){
        int i = hxProTreeMapper.delete(hxProTree.getId());
        if (i !=1){
            throw new BDException("删除失败");
        }
    }

    public void batchDeleteProTree(int[] ids) {
        int count = hxProTreeMapper.bantchDelete(ids);
        if (count < 1){
            throw new BDException("删除失败");
        }
    }
}
