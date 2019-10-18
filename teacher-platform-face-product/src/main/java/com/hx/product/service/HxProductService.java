package com.hx.product.service;

import com.hx.back.entity.HxProTree;
import com.hx.back.entity.HxProduct;
import com.hx.back.entity.HxProductDTO;
import com.hx.back.mapper.HxProTreeMapper;
import com.hx.back.mapper.HxProductMapper;
import com.hx.common.exception.BDException;
import com.hx.common.utils.ListUtils;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HxProductService {

    @Autowired
    private HxProductMapper hxProductMapper;
    @Autowired
    private HxProTreeMapper HxProTreeMapper;

    public R selectAllByPage(HxProductDTO hxProductDTO) {
        List<HxProductDTO> productList = hxProductMapper.selectAllByPage(hxProductDTO);
        List<HxProTree> hxProTrees = HxProTreeMapper.findWithResult(null);
        for (HxProductDTO hxProductDTO1 : productList){
            for (HxProTree hxProTree : hxProTrees){
                if (hxProTree.getConditionId() == 1 && hxProductDTO1.getProUseType() == hxProTree.getCode()){
                    hxProductDTO1.setBusWithName(hxProTree.getName());
                }
                if (hxProTree.getConditionId() == 2 && hxProductDTO1.getProType() == hxProTree.getCode()){
                    hxProductDTO1.setProWithName(hxProTree.getName());
                }
            }
        }
        if (ListUtils.isEmpty(productList)) {
            throw new BDException("查询失败");
        }
        Integer i  = hxProductMapper.findAllCount();
        return R.ok(productList,i);
    }

    public R selectProByCondition(HxProductDTO hxProductDTO){
        List<HxProductDTO> productList = hxProductMapper.selectAllByPage(hxProductDTO);
        if (ListUtils.isEmpty(productList)) {
            throw new BDException("查询失败");
        }
        Integer i  = hxProductMapper.findPageWithCount(hxProductDTO);
        return R.ok(productList,i);
    }

    public HxProduct selectDetile(HxProduct hxProduct){

        HxProTree hxProTree = new HxProTree();
        String str = hxProduct.getProId();
        HxProductDTO productDetile = hxProductMapper.selectById(str);

        //查询产品
        if (productDetile.getProUseType() != null){
            hxProTree.setCode(productDetile.getProUseType());
            hxProTree.setConditionId(2);
            HxProTree proName = HxProTreeMapper.selectByCondition(hxProTree);
            productDetile.setProWithName(proName.getName());
        }

        //查询行业
        if (productDetile.getProType() != null){
            hxProTree.setCode(productDetile.getProType());
            hxProTree.setConditionId(1);
            HxProTree busName = HxProTreeMapper.selectByCondition(hxProTree);
            productDetile.setBusWithName(busName.getName());
        }

        if (productDetile == null) {
            throw new BDException("查询失败");
        }

        return productDetile;
    }

    public List<HxProTree> selectProTree(HxProTree hxProTree){
        List<HxProTree> hxProTreeList = HxProTreeMapper.findWithResult(hxProTree);
        if (ListUtils.isEmpty(hxProTreeList)) {
            throw new BDException("查询失败");
        }
       return hxProTreeList;
    }

    public R selectProTreeByPage(HxProTree hxProTree){
        List<HxProTree> hxProTreeList = HxProTreeMapper.findWithResultByPage(hxProTree);
        if (ListUtils.isEmpty(hxProTreeList)) {
            throw new BDException("查询失败");
        }
        int i = HxProTreeMapper.findCount(hxProTree);
       return R.ok(hxProTreeList,i);
    }


}
