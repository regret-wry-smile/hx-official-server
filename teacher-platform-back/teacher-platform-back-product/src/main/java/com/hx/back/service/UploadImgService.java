package com.hx.back.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.hx.back.entity.HxPictrue;
import com.hx.common.config.BootdoConfig;
import com.hx.common.config.Constant;
import com.hx.common.exception.BDException;
import com.hx.common.fastdfs.FastfdsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class UploadImgService {

    @Autowired
    private FastfdsClient fastfdsClient;
    public HxPictrue uplaodImg(MultipartFile file) throws Exception {

        HxPictrue hxPictrue = new HxPictrue();
        String fileName =file.getOriginalFilename();
        //判断是否有文件且是否为图片文件
        if (!isImageFile(fileName)){
            throw new BDException("请上传图片类型...");
        }else if (fileName!=null && !"".equalsIgnoreCase(fileName.trim())){
            fileName = System.currentTimeMillis() + getFileType(fileName);

            StorePath upload = fastfdsClient.upload(file);
            hxPictrue.setPicName(fileName);
            hxPictrue.setPicAddr(upload.getFullPath());
        }
        return hxPictrue;
    }


    private Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }


}
