package com.hx.controller;

import com.hx.domain.R;
import com.hx.pojo.ProductWeb;
import com.hx.service.ProductWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/productweb")
public class ProductWebController {
    @Autowired
    ProductWebService productWebService;

    @RequestMapping("/query_by_page")
    public R queryProductWeb(@RequestBody(required = false) Map<String, Object> params){
        return R.ok(productWebService.listPage(params),productWebService.selectCount(params));
    }
    @RequestMapping("/query")
    public R queryProductWeb( @RequestBody ProductWeb productWeb){
        return R.ok(productWebService.queryProductWeb(productWeb));
    }
}
