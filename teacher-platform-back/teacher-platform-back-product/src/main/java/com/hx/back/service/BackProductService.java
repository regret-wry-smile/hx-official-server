package com.hx.back.service;

import com.hx.back.entity.HxProduct;
import com.hx.back.mapper.HxProductMapper;
import com.hx.common.config.BootdoConfig;
import com.hx.common.exception.BDException;
import com.hx.common.fastdfs.FastfdsClient;
import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.common.utils.StringUtils;
import com.hx.common.utils.UUID;
import com.hx.domain.HxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BackProductService {

    @Autowired
    private HxProductMapper hxProductMapper;
    @Autowired
    private FastfdsClient fastfdsClient;

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
        //文件删除
        HxProduct hxPro = hxProductMapper.selectById(hxProduct);
        delFileByObject(hxPro);

        int i = hxProductMapper.delete(hxProduct.getId());
        if (i != 1){
            throw new BDException("删除失败");
        }

    }

    public void batchDeleteProduct(int[] ids){
        List<HxProduct> hxProducts = hxProductMapper.selectByIds(ids);
        for (HxProduct hxProduct : hxProducts){
            delFileByObject(hxProduct);
        }

        int count = hxProductMapper.bantchDelete(ids);
        if (count < 1){
            throw new BDException("删除失败");
        }

    }

    /**
     * 根据对象删除产品中的文件
     * @param hxProduct
     * @return
     */
    public void delFileByObject(HxProduct hxProduct){
        if (!StringUtils.isEmpty(hxProduct.getProImg())){
            fastfdsClient.deleteFile(hxProduct.getProImg());
        }
        if (!StringUtils.isEmpty(hxProduct.getProLogoAddr())){
            fastfdsClient.deleteFile(hxProduct.getProLogoAddr());
        }
        /*if (!StringUtils.isEmpty(hxProduct.getProDetilImg())){
            String[] str = hxProduct.getProDetilImg().split("#");
            for (String s : str){
                 fastfdsClient.deleteFiledelFile(s);
            }
        }*/

    }


}
