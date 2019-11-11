package com.hx.service;

import com.hx.mapper.ProductWebMapper;
import com.hx.pojo.ProductWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductWebService {
    @Autowired
    ProductWebMapper productWebMapper;

    public List<ProductWeb> listPage(Map<String, Object> params) {
        return productWebMapper.listPage(params);
    }

    public Integer selectCount(Map<String, Object> params) {
        return productWebMapper.selectCount(params);
    }

    public ProductWeb queryProductWeb(ProductWeb productWeb) {
        return productWebMapper.selectByPrimaryKey(productWeb.getId());
    }

    public void saveSolution(ProductWeb productWeb) {
        productWebMapper.insertSelective(productWeb);
    }

    public void deleteSolution(List<Integer> ids) {
        productWebMapper.deleteByIds(ids);
    }

    public void removeSolution(Integer id) {
        productWebMapper.deleteByPrimaryKey(id);
    }

    public void updateSolution(ProductWeb productWeb) {
        productWebMapper.updateByPrimaryKeySelective(productWeb);
    }
}
