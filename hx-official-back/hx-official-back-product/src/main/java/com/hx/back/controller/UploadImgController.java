package com.hx.back.controller;

import com.hx.back.entity.HxPictrue;
import com.hx.back.service.UploadImgService;
import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadImgController {

    @Autowired
    private UploadImgService uploadImgService;

    @RequestMapping("/uploadImg")
    public R uplaodImg(@RequestParam("imgFile") MultipartFile imgFile) throws Exception {
        return R.ok(uploadImgService.uplaodImg(imgFile));
    }

    /**
     * base64上传接口
     * @param hxPictrue
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImgBase64")
    public R uploadImgBase64(@RequestBody HxPictrue hxPictrue) throws Exception {
        return R.ok(uploadImgService.updatePersonalImg(hxPictrue));
    }

    /**
     * 文件删除接口
     * @param fileAddr
     * @return
     */
    @RequestMapping("/deleteFileAddr")
    public R deleteFileAddr(@RequestBody String fileAddr){
        uploadImgService.deleteFileAddr(fileAddr);
        return R.ok();
    }
}
