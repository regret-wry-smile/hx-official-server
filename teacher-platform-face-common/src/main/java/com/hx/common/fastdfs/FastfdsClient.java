package com.hx.common.fastdfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.hx.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * fastfds上传、下载封装客户端
 *
 * @author zw
 */
@Component
public class FastfdsClient {
    private Logger logger = LoggerFactory.getLogger(FastfdsClient.class);

    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    /**
     * 上传文件
     *
     * @param local_filename
     * @throws IOException
     * @throws Exception
     */
    public StorePath upload(String local_filename) throws Exception {
        return upload(new File(local_filename));
    }

    public StorePath upload(File file) throws Exception {
        InputStream is = null;
        try{
            is = new FileInputStream(file);
            StorePath storePath = upload(is, file.length(), file.getName());
            return storePath;
        } finally {
            if (is != null){
                is.close();
            }
        }


    }
    public StorePath upload(InputStream inputStream,long length,String fileName){
        StorePath storePath = storageClient.uploadFile(
                inputStream, length, getFileExtension(fileName), null);
        return storePath;
    }
    /**
     * 上传文件
     */
    public String uploadImageAndCrtThumbImage(MultipartFile multipartFile) throws Exception{
        String originalFilename = multipartFile.getOriginalFilename().
                substring(multipartFile.getOriginalFilename().
                        lastIndexOf(".") + 1);
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(
                multipartFile.getInputStream(),
                multipartFile.getSize(),originalFilename , null);
        return storePath.getFullPath() ;
    }
    /**
     * 上传文件
     */
    public StorePath upload(MultipartFile multipartFile) throws Exception{
        String originalFilename = multipartFile.getOriginalFilename().
                substring(multipartFile.getOriginalFilename().
                        lastIndexOf(".") + 1);
        StorePath storePath = storageClient.uploadFile(
                multipartFile.getInputStream(),
                multipartFile.getSize(),originalFilename , null);
        return storePath ;
    }
    /**
     * 删除文件
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            throw new IllegalArgumentException("文件路径为空");
        }
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        try {
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsServerException e){
            logger.error("未成功删除服务器上的文件:{}",fileUrl,e);
        }
    }
    /**
     * 下载文件
     */
    public byte[] download(String fileUrl){
        if (StringUtils.isEmpty(fileUrl)) {
            throw new IllegalArgumentException("文件路径为空");
        }
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes =storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), downloadByteArray);
        return bytes;
    }

    /**
     * 获得文件后缀
     *
     * @param filePath
     * @return
     */
    private static String getFileExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("文件路径为空");
        }
        int extenPosi = filePath.lastIndexOf(".");
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
             throw new IllegalArgumentException("未获取到扩展名, path : " +filePath);
        }
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }

    private static String getFolderName(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            filePosi = filePath.lastIndexOf("/");
        }
        if (filePosi == -1) {
            filePosi = filePath.lastIndexOf("\\");
        }
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

}
