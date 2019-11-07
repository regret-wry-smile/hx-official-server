package com.hx.external.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.hx.common.config.BootdoConfig;
import com.hx.common.config.Constant;
import com.hx.common.exception.BDException;
import com.hx.common.fastdfs.FastfdsClient;
import com.hx.common.utils.FileType;
import com.hx.common.utils.StringUtils;
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
    private FastfdsClient fastfdsClient;
    @Autowired
    BootdoConfig bootConfig;

    @Override
    public External UploadExternal(MultipartFile file){
        try {
            External external = new External();
            if (file.isEmpty()) {
                throw new BDException("文件为空");
            }
            StorePath storePath = fastfdsClient.upload(file);
            String interfaceName = file.getOriginalFilename();
            if (FileType.fileType(interfaceName)!=1){
                throw new BDException("请上传文档文件");
            }
            String string = interfaceName.substring(0,interfaceName.lastIndexOf("."));
            external.setInterfaceName(string);
            external.setInterfaceAddress(storePath.getFullPath());
            return external;
        } catch (Exception e) {
            throw new BDException("服务器异常，请联系管理员");
        }
    }

    @Override
    public void insertExternal(External external){
        if (StringUtils.isEmpty(external.getProjectType())){
            throw new BDException("项目名称未选");
        }
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateString = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        Date date1 = dateFormat.parse(dateString,pos);
        external.setCreateDate(date1);
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
                String interfaceAddress  = bootConfig.getUploadPath()+ external.getInterfaceAddress();
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
    public void deleteExternal(External external){
        External externals = externalMapper.selectById(external.getId());
        fastfdsClient.deleteFile(externals.getInterfaceAddress());
        int i = externalMapper.deleteExt(external);
        if (i < 1){
            throw new BDException("删除失败");
        }
    }

    @Override
    public void deleteExternals(int[] ids){
        for (int i = 0; i <ids.length ; i++) {
            int j = ids[i];
            External external = externalMapper.selectById(j);
            fastfdsClient.deleteFile(external.getInterfaceAddress());
            externalMapper.delete(j);
            if (j < 1){
                throw new BDException("删除失败");
            }
        }
    }

    @Override
    public void updateExternal(External external){
        int i = externalMapper.update(external);
        if (i !=1 ){
            throw new BDException("编辑失败");
        }
    }

}
