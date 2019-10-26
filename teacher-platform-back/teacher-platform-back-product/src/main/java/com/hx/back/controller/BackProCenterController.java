package com.hx.back.controller;

import com.hx.back.entity.HxProCenter;
import com.hx.back.service.BackProCenterService;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backProCenter")
public class BackProCenterController {

    @Autowired
    private BackProCenterService backProCenterService;

    @RequestMapping("/addProCenter")
    public R addProCenter(@RequestBody HxProCenter hxProCenter){
        backProCenterService.addProCenter(hxProCenter);
        return R.ok();
    }

    @RequestMapping("/updateProCenter")
    public R updateProCenter(@RequestBody HxProCenter hxProCenter){
        backProCenterService.updateProCenter(hxProCenter);
        return R.ok();
    }

    @RequestMapping("/deleteProCenter")
    public R deleteProCenter(@RequestBody HxProCenter hxProCenter){
        backProCenterService.deleteProCenter(hxProCenter);
        return R.ok();
    }

    @RequestMapping("/deleteProCenters")
    public R deleteProCenters(@RequestBody int[] ids){
        backProCenterService.deleteProCenters(ids);
        return R.ok();
    }

}
