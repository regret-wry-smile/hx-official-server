package com.hx.back.service;

import com.hx.back.entity.HxProduct;
import com.hx.back.mapper.HxProductMapper;
import com.hx.common.config.BootdoConfig;
import com.hx.common.exception.BDException;
import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.common.utils.UUID;
import com.hx.domain.HxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BackProductService {

    @Autowired
    private HxProductMapper hxProductMapper;
    @Autowired
    private BootdoConfig bootConfig;

    public void insertProduct(HxProduct hxProduct){
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hxProduct.setCreateTime(sf.format(new Date()));
        hxProduct.setProId(UUID.get32UUID());
        hxProduct.setCreateUser(user.getUserName());
        int i = hxProductMapper.insertDynamic(hxProduct);
        if (i != 1){
            throw new BDException("添加失败");
        }
    }

    public void updateProduct(HxProduct hxProduct){
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hxProduct.setUpdateTime(sf.format(new Date()));
        hxProduct.setUpdateUser(user.getUserName());
        int i = hxProductMapper.updateDynamic(hxProduct);
        if (i != 1){
            throw new BDException("更新失败");
        }
    }

    public void deleteProduct(HxProduct hxProduct){

        int i = hxProductMapper.delete(hxProduct.getId());
        if (i != 1){
            throw new BDException("删除失败");
        }

    }

    public void batchDeleteProduct(int[] ids){
        int count = hxProductMapper.bantchDelete(ids);
        if (count < 1){
            throw new BDException("删除失败");
        }
    }

    /**
     * 文件删除
     * @param fileUrl
     * @return
     */
    static boolean delFile(String fileUrl) {
        File file = new File(fileUrl);
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f.getName());
            }
        }
        return file.delete();
    }


}
