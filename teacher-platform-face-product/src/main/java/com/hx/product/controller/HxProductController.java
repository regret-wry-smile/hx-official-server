package com.hx.product.controller;

import com.hx.back.entity.HxProduct;
import com.hx.domain.R;
import com.hx.product.service.HxProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pro")
public class HxProductController {

    @Autowired
    private HxProductService hxProductService;

    /**
     * 查询限制条数的产品或查询产品详情
     * @param hxProduct
     * @return
     */
    @RequestMapping("/selectResultLimit")
    public R selectResultLimit(@RequestBody HxProduct hxProduct){
        return R.ok(hxProductService.selectResultLimit(hxProduct));
    }

    /**
     * 查询所有的产品信息
     * @return
     */
    @RequestMapping("/selectAll")
    public R selectAll() {
        return R.ok(hxProductService.selectAll());
    }

}
