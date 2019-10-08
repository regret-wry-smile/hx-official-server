package com.hx.external.service.impl;

import com.hx.common.config.BootdoConfig;
import com.hx.common.exception.BDException;
import com.hx.external.mapper.ExternalMapper;
import com.hx.external.domain.External;
import com.hx.external.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private ExternalMapper externalDao;

    @Autowired
    BootdoConfig bootConfig;

    @Override
    public External UploadExternal(MultipartFile file){
        try {
            External external = new External();
            if (file.isEmpty()) {
                throw new BDException("文件为空");
            }
            byte[] bytes = file.getBytes();
            String address = bootConfig.getUploadPath()+"External/";
            String fileAddress = "External/"+file.getOriginalFilename();
            if (createDir(address)) {
                Path path = Paths.get(bootConfig.getUploadPath()+fileAddress);
                Files.write(path, bytes);
            }else {
                throw new BDException("文件路径创建失败");
            }
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String dateString = dateFormat.format(date);
            ParsePosition pos = new ParsePosition(0);
            Date date1 = dateFormat.parse(dateString,pos);
            external.setInterfaceName(file.getOriginalFilename());
            external.setInterfaceAddress(fileAddress);
            external.setCreateDate(date1);
            return external;
        } catch (IOException e) {
            throw new BDException("服务器异常，请联系管理员");
        }
    }

    @Override
    public void InsertExternal(External external){
        int i = externalDao.insertDynamic(external);
        if (i != 1){
            throw new BDException("添加失败");
        }
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {// 判断目录是否存在
            return true;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        if (dir.mkdirs()) {// 创建目标目录
            System.out.println("创建目录成功！" + destDirName);
            return true;
        } else {
            System.out.println("创建目录失败！");
            return false;
        }
    }

    @Override
    public HashMap SelectExternal(List external){
        HashMap<String,Object> type = new HashMap<>();
        for (int i = 0; i <external.size() ; i++) {
            String projectType = external.get(i).toString();
            List<External> externals = externalDao.selectByType(projectType);
            type.put(projectType,externals);
        }
        return type;
    }


}
