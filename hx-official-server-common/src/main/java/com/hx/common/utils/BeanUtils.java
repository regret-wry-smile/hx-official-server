package com.hx.common.utils;

import com.google.common.collect.Maps;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zw
 * @date 2019/8/22 9:27
 */
public class BeanUtils {
    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) throws InvocationTargetException, IllegalAccessException {
//        BeanMap beanMap = BeanMap.create(bean);
//        beanMap.putAll(map);
//        return bean;
        org.apache.commons.beanutils.BeanUtils.populate(bean, map);
        return bean;
    }
    public static <T> List<T> mapToListBean(Object source, Class<T> bean) throws Exception {
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> type, Object value) {
                String dateStr = (String) value;
                if (StringUtils.isNotEmpty(dateStr)) {
                    SimpleDateFormat spdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        return (T) spdt.parse(dateStr);
                    } catch (ParseException e) {
                        SimpleDateFormat spdt2 = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                           return (T) spdt2.parse(dateStr);
                        } catch (ParseException e1) {
                            throw new RuntimeException("日期转换错误", e1);
                        }
                    }
                }
                return null;
            }
        }, Date.class);
       List<T> listBean = new ArrayList<>();
        if (source != null){
            if (source instanceof List){
                List<Map<String, Object>> list = null;
                try {
                    list = (List<Map<String, Object>>) source;
                } catch (Exception e){
                    throw new Exception("list 中的对象必须是Map类型");
                }
                for (Map<String, Object> map : list) {
                    listBean.add(mapToBean(map, bean.newInstance()));
                }
            }else if (source instanceof Map){
                listBean.add(mapToBean((Map<String, Object>)source, bean.newInstance()));
            }else {
                throw new Exception("参数 必须是 Map类型 或 List<Map<String,Object> 类型");
            }

        }
        return listBean;
    }
}
