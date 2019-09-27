package com.hx.product.service;

import com.hx.back.entity.HxProduct;
import com.hx.back.mapper.HxProductMapper;
import com.hx.common.exception.BDException;
import com.hx.common.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HxProductService {

    @Autowired
    private HxProductMapper hxProductMapper;

    public List<HxProduct> selectResultLimit(HxProduct hxProduct){
        List<HxProduct> productList = hxProductMapper.findResultLimit(hxProduct);
        if (ListUtils.isEmpty(productList)) {
            throw new BDException("查询失败");
        }
        return productList;
    }

    public List<HxProduct> selectAll() {
        List<HxProduct> productList = hxProductMapper.findAllResult();
        if (ListUtils.isEmpty(productList)) {
            throw new BDException("查询失败");
        }
        return productList;
    }

}
