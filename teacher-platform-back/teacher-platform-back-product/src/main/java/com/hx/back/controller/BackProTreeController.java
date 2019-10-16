package com.hx.back.controller;

import com.hx.back.entity.HxProTree;
import com.hx.back.service.BackProTreeService;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proTree")
public class BackProTreeController {

    @Autowired
    private BackProTreeService backProTreeService;

    /**
     * 新增产品数据字典
     * @param hxProTree
     * @return
     */
    @RequestMapping("/insertProTree")
    public R insertProTree(@RequestBody HxProTree hxProTree){
        backProTreeService.insertProTree(hxProTree);
        return R.ok();
    }

    /**
     * 修改产品数据字典
     * @param hxProTree
     * @return
     */
    @RequestMapping("/updateProTree")
    public R updateProTree(@RequestBody HxProTree hxProTree){
        backProTreeService.updateProTree(hxProTree);
        return R.ok();
    }

    /**
     * 删除产品数据字典
     * @param hxProTree
     * @return
     */
    @RequestMapping("/deleteProTree")
    public R deleteProTree(@RequestBody HxProTree hxProTree){
        backProTreeService.deleteProTree(hxProTree);
        return R.ok();
    }

}
