package com.hx.external.controller;

import com.hx.domain.R;
import com.hx.external.domain.External;
import com.hx.external.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private ExternalService externalService;

    @PostMapping("/uploadFile")
    public R singleFileUpload(@RequestParam("file") MultipartFile file) {
        External external = externalService.UploadExternal(file);
        return R.ok(external);
    }

}
