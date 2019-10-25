package com.hx.back.controller;

import com.hx.back.entity.HxProCenterTree;
import com.hx.back.service.BackProCenterTreeService;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ProCenterTree")
public class BackProCenterTreeController {

    @Autowired
    private BackProCenterTreeService backProCenterTreeService;

    @RequestMapping("/addProCenterTree")
    public R addProCenterTree(@RequestBody HxProCenterTree hxProCenterTree){
        backProCenterTreeService.addProCenterTree(hxProCenterTree);
        return R.ok();
    }

    @RequestMapping("/updateProCenterTree")
    public R updateProCenterTree(@RequestBody HxProCenterTree hxProCenterTree){
        backProCenterTreeService.updateProCenterTree(hxProCenterTree);
        return R.ok();
    }

    @RequestMapping("/deleteProCenterTree")
    public R deleteProCenterTree(@RequestBody int id){
        backProCenterTreeService.deleteProCenterTree(id);
        return R.ok();
    }

    @RequestMapping("/deleteProCenterTrees")
    public R deleteProCenterTrees(@RequestBody int[] ids){
        backProCenterTreeService.deleteProCenterTrees(ids);
        return R.ok();
    }

}
