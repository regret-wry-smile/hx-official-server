package com.hx.external.service.impl;

import com.hx.common.config.BootdoConfig;
import com.hx.common.config.Constant;
import com.hx.common.exception.BDException;
import com.hx.common.utils.ListUtils;
import com.hx.external.domain.ExternalDTO;
import com.hx.external.domain.Item;
import com.hx.external.domain.Module;
import com.hx.external.mapper.ExternalMapper;
import com.hx.external.domain.External;
import com.hx.external.mapper.ModuleMapper;
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
import java.util.*;

@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private ExternalMapper externalMapper;
    @Autowired
    private ModuleMapper moduleMapper;
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
            String fileAddress = "/External/"+file.getOriginalFilename();
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
        int i = externalMapper.insertDynamic(external);
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
    public List<Item> SelectExternal(List<Module> modules ){
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i <modules.size() ; i++) {
            Item item = new Item();
            String projectType = modules.get(i).getProjectType();
            String projectName = modules.get(i).getProjectName();
            item.setTitle(projectName);
            List<External> externals = externalMapper.selectByType(projectType);
            for (int j = 0; j <externals.size() ; j++) {
                External external = externals.get(j);
                String interfaceAddress  = bootConfig.getPath()+Constant.REQUEST_FILE_PREFIX_LOCAL+ external.getInterfaceAddress();
                externals.get(j).setInterfaceAddress(interfaceAddress);
            }
            item.setList(externals);
            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public List<ExternalDTO> selectByPage(ExternalDTO externalDTO){
        List<ExternalDTO> externalDTOS = externalMapper.list(externalDTO);
        for (int i=0;i<externalDTOS.size();i++){
            Module module = new Module();
            module.setProjectType(externalDTOS.get(i).getProjectType());
            Module module1 = moduleMapper.selectByModule(module);
            externalDTOS.get(i).setProjectName(module1.getProjectName());
        }
        return externalDTOS;
    }

    @Override
    public int count(ExternalDTO externalDTO){
        int i = externalMapper.count(externalDTO);
        return i;
    }

    @Override
    public void deleteModule(External external){
        int i = externalMapper.deleteExt(external);
        if (i < 1){
            throw new BDException("删除失败");
        }
    }

    @Override
    public void deleteModules(int[] ids){
        int j = externalMapper.deleteByIds(ids);
        if (j < 1){
            throw new BDException("删除失败");
        }
    }

}
