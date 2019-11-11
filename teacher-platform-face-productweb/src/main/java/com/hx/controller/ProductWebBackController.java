package com.hx.controller;

import com.hx.domain.R;
import com.hx.pojo.ProductWeb;
import com.hx.service.ProductWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Arrays;

@RestController
@RequestMapping("/backproductweb")
public class ProductWebBackController {
    @Autowired
    ProductWebService productWebService;

    @RequestMapping("/add")
    public R saveProductWeb(@RequestBody ProductWeb productWeb) throws ParseException {
        productWebService.saveSolution(productWeb);
        return R.ok();
    }

    @RequestMapping("/batchremove")
    public R deleteProductWeb(@RequestBody Integer[]  ids){
        productWebService.deleteSolution(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/remove")
    public R removeProductWeb(@RequestBody ProductWeb productWeb){
        productWebService.removeSolution(productWeb.getId());
        return R.ok();
    }

    @RequestMapping("/update")
    public R updateProductWeb(@RequestBody ProductWeb productWeb)  {
        productWebService.updateSolution(productWeb);
        return R.ok();
    }
}
