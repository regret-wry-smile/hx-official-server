package com.hx.service;

import com.hx.mapper.ProductWebMapper;
import com.hx.mapper.ProductwebCategoryMapper;
import com.hx.pojo.ProductWeb;
import com.hx.pojo.ProductWebVo;
import com.hx.pojo.ProductwebCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductWebService {
    @Autowired
    ProductWebMapper productWebMapper;
    @Autowired
    ProductwebCategoryMapper productwebCategoryMapper;

    public List<ProductWebVo> listPage(Map<String, Object> params) {
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

    public List<ProductwebCategory> listPageCategory(Map<String, Object> params) {
        return productwebCategoryMapper.listPage(params);
    }

    public Integer selectCountCategory(Map<String, Object> params) {
        return productwebCategoryMapper.selectCount(params);
    }

    public ProductwebCategory queryProductWebCategory(ProductwebCategory productwebCategory) {
        return productwebCategoryMapper.selectByPrimaryKey(productwebCategory.getId());
    }

    public void saveProductWebCategory(ProductwebCategory productwebCategory) {
        int code = productwebCategoryMapper.getMaxCode(productwebCategory);
        productwebCategory.setCode(code+1);
        productwebCategoryMapper.insertSelective(productwebCategory);
    }

    public void deleteProductWebCategory(List<Integer> ids) {
        productwebCategoryMapper.deleteByIds(ids);
    }

    public void removeProductWebCategory(Integer id) {
        productwebCategoryMapper.deleteByPrimaryKey(id);
    }

    public void updateProductWebCategory(ProductwebCategory productwebCategory) {
        productwebCategoryMapper.updateByPrimaryKeySelective(productwebCategory);
    }
}
