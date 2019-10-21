package com.hx.back.service;

import com.hx.back.entity.HxPictrue;
import com.hx.common.config.BootdoConfig;
import com.hx.common.config.Constant;
import com.hx.common.exception.BDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class UploadImgService {

    @Autowired
    private BootdoConfig bootConfig;

    public HxPictrue uplaodImg(MultipartFile file){

        HxPictrue hxPictrue = new HxPictrue();
        try {
            String fileName =file.getOriginalFilename();
            //判断是否有文件且是否为图片文件
            if (!isImageFile(fileName)){
                throw new BDException("请上传图片类型...");
            }else if (fileName!=null && !"".equalsIgnoreCase(fileName.trim())){
                fileName = System.currentTimeMillis() + getFileType(fileName);
                String destFileName = "pictrue/" +  fileName;
                //第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
                File destFile = new File(bootConfig.getUploadPath() + destFileName);
                if (!destFile.exists()){
                    destFile.getParentFile().mkdirs();
                }
                //把图片复制到指定的位置
                file.transferTo(destFile);
                hxPictrue.setPicName(fileName);
                hxPictrue.setPicAddr(destFileName);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (hxPictrue == null && hxPictrue.getPicName() == null){
            throw new BDException("上传失败...");
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
