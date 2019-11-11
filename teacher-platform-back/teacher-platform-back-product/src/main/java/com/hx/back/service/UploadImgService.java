package com.hx.back.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.hx.back.entity.HxPictrue;
import com.hx.common.config.BootdoConfig;
import com.hx.common.config.Constant;
import com.hx.common.exception.BDException;
import com.hx.common.fastdfs.FastfdsClient;
import com.hx.common.utils.FileType;
import com.hx.common.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
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
        if (FileType.fileType(fileName)!=0){
            throw new BDException("请上传图片类型...");
        }else if (fileName!=null && !"".equalsIgnoreCase(fileName.trim())){
            fileName = System.currentTimeMillis() + getFileType(fileName);
            StorePath upload = fastfdsClient.upload(file);
            hxPictrue.setPicAddr(upload.getFullPath());
        }
        return hxPictrue;
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

    /*base64上传*/
    @Transactional(rollbackFor = Exception.class)
    public HxPictrue updatePersonalImg(HxPictrue hxPictrue) throws Exception {
        String file = hxPictrue.getPicAddr();
        if (StringUtils.isEmpty(file)) {
            throw new BDException("文件不能为空");
        }
        String[] split = file.split(",");
        if (split == null || split.length != 2){
            throw new Exception("base64图片格式不正确切割出错 : "+file);
        }
        byte[] bytes = null;
        try {
            bytes = Base64.decodeBase64(split[1]);
        } catch (Exception e) {
            throw new Exception("base64图片解析错误 : "+split[1]);
        }
        StorePath  storePath = fastfdsClient.upload(new ByteArrayInputStream(bytes), bytes.length, "."+split[0].replace("data:image/", "").replace(";base64", ""));
        hxPictrue.setPicAddr(storePath.getFullPath());
        if (!StringUtils.isEmpty(hxPictrue.getOldPicAddr())){
            fastfdsClient.deleteFile(hxPictrue.getOldPicAddr());
        }
        return hxPictrue;
    }

    /*删除文件*/
    public void deleteFileAddr(String imgAddr) {
        if (!StringUtils.isEmpty(imgAddr)){
            fastfdsClient.deleteFile(imgAddr);
        }
    }
}
