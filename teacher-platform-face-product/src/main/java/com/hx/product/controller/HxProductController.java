package com.hx.product.controller;

import com.hx.back.entity.HxProTree;
import com.hx.back.entity.HxProduct;
import com.hx.back.entity.HxProductDTO;
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
    public R selectAllByPage(@RequestBody HxProductDTO hxProductDTO) {
        return hxProductService.selectAllByPage(hxProductDTO);
    }

    /**
     * 查询产品数据结构
     * @return
     */
    @RequestMapping("/selectProList")
    public R selectProList(@RequestBody HxProTree hxProTree) {
        return R.ok(hxProductService.selectProTree(hxProTree));
    }

    /**
     * 分页查询产品数据结构
     * @return
     */
    @RequestMapping("/selectProTreeByPage")
    public R selectProTreeByPage(@RequestBody HxProTree hxProTree) {
        return hxProductService.selectProTreeByPage(hxProTree);
    }

}
