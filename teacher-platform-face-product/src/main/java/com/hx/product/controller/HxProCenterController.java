package com.hx.product.controller;

import com.hx.back.entity.HxProCenter;
import com.hx.back.entity.HxProCenterDTO;
import com.hx.back.entity.HxProCenterTree;
import com.hx.back.entity.HxProductDTO;
import com.hx.domain.R;
import com.hx.product.service.HxProCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proCenter")
public class HxProCenterController {

    @Autowired
    private HxProCenterService hxProCenterService;

    /**
     * 分页查询
     * @param hxProCenterDTO
     * @return
     */
    @RequestMapping("/findProCenterByPage")
    public R findProCenterByPage(@RequestBody HxProCenterDTO hxProCenterDTO){
        return hxProCenterService.findProCenterByPage(hxProCenterDTO);
    }

    /**
     * 查询树结构
     * @param hxProCenterTree
     * @return
     */
    @RequestMapping("/findProCenterTree")
    public R findProCenterTree(@RequestBody HxProCenterTree hxProCenterTree){
        return R.ok(hxProCenterService.findProCenterTree(hxProCenterTree));
    }

    /**
     * 查询产品详情
     * @param hxProCenter
     * @return
     */
    @RequestMapping("/findProDetil")
    public R findProDetil(@RequestBody HxProCenter hxProCenter){
        return R.ok(hxProCenterService.findProDetil(hxProCenter));
    }

}
