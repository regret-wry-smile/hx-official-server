package com.hx.back.controller;

import com.hx.back.service.UploadImgService;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadImgController {

    @Autowired
    private UploadImgService uploadImgService;

    @RequestMapping("/uploadImg")
    public R uplaodImg(@RequestParam("imgFile") MultipartFile imgFile) {
        return R.ok(uploadImgService.uplaodImg(imgFile));
    }
}
