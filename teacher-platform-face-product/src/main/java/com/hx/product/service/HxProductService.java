package com.hx.product.service;

import com.hx.back.entity.HxProTree;
import com.hx.back.entity.HxProduct;
import com.hx.back.entity.HxProductDTO;
import com.hx.back.mapper.HxProTreeMapper;
import com.hx.back.mapper.HxProductMapper;
import com.hx.common.exception.BDException;
import com.hx.common.utils.ListUtils;
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

    public HxProductDTO selectAllByPage(Map<String,Object> object) {
        HxProductDTO hxProductDTO = new HxProductDTO();
        List<HxProduct> productList = hxProductMapper.selectAllByPage(object);
        if (ListUtils.isEmpty(productList)) {
            throw new BDException("查询失败");
        }
        int num = hxProductMapper.findPageWithCount(null);
        hxProductDTO.setData(productList);
        hxProductDTO.setCountNum(num);
        return hxProductDTO;
    }

    public HxProductDTO selectProByCondition(Map<String,Object> object){
        HxProductDTO hxProductDTO = new HxProductDTO();
        List<HxProduct> productList = hxProductMapper.selectAllByPage(object);
        if (ListUtils.isEmpty(productList)) {
            throw new BDException("查询失败");
        }
        HxProduct hxProduct = new HxProduct();
        if (object.get("proTitle") != null){
            hxProduct.setProTitle(object.get("proTitle").toString());
        }
        if (object.get("proUserType") != null){
            hxProduct.setProUseType((Integer) object.get("proUserType"));
        }
        if (object.get("protype") != null){
            hxProduct.setProType((Integer) object.get("protype"));
        }
        int num = hxProductMapper.findPageWithCount(hxProduct);
        hxProductDTO.setData(productList);
        hxProductDTO.setCountNum(num);
        return hxProductDTO;
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


}
