package com.hx.product.service;

import com.hx.back.entity.HxProCenter;
import com.hx.back.entity.HxProCenterDTO;
import com.hx.back.entity.HxProCenterTree;
import com.hx.back.mapper.HxProCenterMapper;
import com.hx.back.mapper.HxProCenterTreeMapper;
import com.hx.common.exception.BDException;
import com.hx.common.utils.ListUtils;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HxProCenterService {

    @Autowired
    private HxProCenterMapper hxProCenterMapper;
    @Autowired
    private HxProCenterTreeMapper hxProCenterTreeMapper;

    public R findProCenterByPage(HxProCenterDTO hxProCenterDTO) {
        List<HxProCenterDTO> hxProCenterDTOS = hxProCenterMapper.findPageWithResult(hxProCenterDTO);
        if (ListUtils.isEmpty(hxProCenterDTOS)){
            throw new BDException("查询产品失败");
        }
        int count = hxProCenterMapper.findPageWithCount(hxProCenterDTO);
        return R.ok(hxProCenterDTOS,count);
    }

    public R findProCenterTree(HxProCenterTree hxProCenterTree) {
        List<HxProCenterTree> hxProCenterTrees = hxProCenterTreeMapper.findPageWithResult(hxProCenterTree);
        if (ListUtils.isEmpty(hxProCenterTrees)){
            throw new BDException("查询产品分类数据失败");
        }
        int count = hxProCenterTreeMapper.findPageWithCount(hxProCenterTree);
        return R.ok(hxProCenterTrees,count);
    }

    public HxProCenter findProDetil(HxProCenter hxProCenter) {
        HxProCenter hxProDetil = hxProCenterMapper.selectById(hxProCenter);
        if (hxProDetil == null){
            throw new BDException("查询产品失败");
        }
        return hxProDetil;
    }

}
