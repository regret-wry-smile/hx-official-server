package com.hx.external.controller;

import com.hx.domain.R;
import com.hx.external.domain.External;
import com.hx.external.domain.ExternalDTO;
import com.hx.external.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("External")
public class UploadController {

    @Autowired
    private ExternalService externalService;

    //后台文件上传到本地
    @PostMapping("/uploadFile")
    public R singleFileUpload(@RequestParam("file") MultipartFile file) {
        External external = externalService.UploadExternal(file);
        return R.ok(external);
    }

    //后台添加API文档信息
    @RequestMapping("/insertExternal")
    public R insertExternal(@RequestBody External external){
        externalService.insertExternal(external);
        return R.ok();
    }

    //后台删除API文档
    @RequestMapping("/deleteExternal")
    public R deleteExternal(@RequestBody External external){
        externalService.deleteExternal(external);
        return R.ok();
    }

    //后台批量删除API文档
    @RequestMapping("/deleteExternals")
    public R deleteExternals(@RequestBody int[] ids){
        externalService.deleteExternals(ids);
        return R.ok();
    }

    //后台修改API文档
    @RequestMapping("/updateExternal")
    public R updateExternal(@RequestBody External external){
        externalService.updateExternal(external);
        return R.ok();
    }

    //后台分页查询API文档
    @RequestMapping("/selectByPage")
    public  R selectByPage(@RequestBody ExternalDTO externalDTO){
        List<ExternalDTO> externalDTOS = externalService.selectByPage(externalDTO);
        int i = externalService.count(externalDTO);
        return R.ok(externalDTOS,i);
    }

}
