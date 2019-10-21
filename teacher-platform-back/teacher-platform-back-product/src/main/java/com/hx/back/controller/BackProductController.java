package com.hx.back.controller;

import com.hx.back.entity.HxProduct;
import com.hx.back.entity.HxProductDTO;
import com.hx.back.service.BackProductService;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backPro")
public class BackProductController {

    @Autowired
    private BackProductService backProductService;

    /**
     * 新增产品案例
     * @param hxProduct
     * @return
     */
    @PostMapping("/insertProduct")
    public R insertProduct(@RequestBody HxProduct hxProduct){
        backProductService.insertProduct(hxProduct);
        return R.ok();
    }

    /**
     * 修改产品案例
     * @param hxProduct
     * @return
     */
    @PostMapping("/updateProduct")
    public R updateProduct(@RequestBody HxProduct hxProduct){
        backProductService.updateProduct(hxProduct);
        return R.ok();
    }

    /**
     * 删除产品案例
     * @param hxProduct
     * @return
     */
    @PostMapping("/deleteProduct")
    public R deleteProduct(@RequestBody HxProduct hxProduct){
        backProductService.deleteProduct(hxProduct);
        return R.ok();
    }

    /**
     * 批量删除产品案例
     * @param ids
     * @return
     */
    @PostMapping("/batchDeleteProduct")
    public R batchDeleteProduct(@RequestBody int[] ids){
        backProductService.batchDeleteProduct(ids);
        return R.ok();
    }


}
