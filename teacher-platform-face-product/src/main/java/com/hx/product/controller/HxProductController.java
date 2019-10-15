package com.hx.product.controller;

import com.hx.back.entity.HxProTree;
import com.hx.back.entity.HxProduct;
import com.hx.domain.R;
import com.hx.product.service.HxProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("pro")
public class HxProductController {

    @Autowired
    private HxProductService hxProductService;

    /**
     * 按条件查询产品
     * @return
     */
    @RequestMapping("/selectProByCondition")
    public R selectResultLimit(@RequestBody Map<String,Object> object){
        return R.ok(hxProductService.selectProByCondition(object));
    }

    /**
     * 查询产品详情
     * @param hxProduct
     * @return
     */
    @RequestMapping("/selectDetile")
    public R selectDetile(@RequestBody HxProduct hxProduct){
        return R.ok(hxProductService.selectDetile(hxProduct));
    }

    /**
     * 分页查询的产品信息
     * @return
     */
    @RequestMapping("/selectAllByPage")
    public R selectAllByPage(@RequestBody Map<String,Object> object) {
        return R.ok(hxProductService.selectAllByPage(object));
    }

    /**
     * 查询产品数据结构
     * @return
     */
    @RequestMapping("/selectProTree")
    public R selectProTree(@RequestBody HxProTree hxProTree) {
        return R.ok(hxProductService.selectProTree(hxProTree));
    }

}
