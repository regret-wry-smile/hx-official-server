package com.hx.service.impl;

import com.hx.common.redis.shiro.ShiroUtils;
import com.hx.domain.HxUser;
import com.hx.mapper.NewsMapper;
import com.hx.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    @Autowired
    NewsMapper newsMapper;

    public void saveNews(News news) throws ParseException {

        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(new Date());
        Date date = sf.parse(s);
        news.setCreateTime(date);
        if(news.getCategory().equals(0)){
            news.setCategory("行业新闻");
        }else if(news.getCategory().equals(1)){
            news.setCategory("公司新闻");
        }else{
            news.setCategory("公司通知");
        }

        String lable = news.getLable();
        //lable.replace("，",",");
        //int on1 = lable.indexOf("，");
        if(lable.contains("，")){
            String lable2 = lable.replace("，",",");
            news.setLable(lable2);
        }
        news.setCreateUser(user.getUserName());
        newsMapper.insertSelective(news);
    }

    public void deleteNews(List<Integer> ids) {
        newsMapper.deleteByIds(ids);
    }

    public void updateNews(News news) throws ParseException{
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(new Date());
        Date date = sf.parse(s);
        news.setUpdateTime(date);

        if(news.getCategory().equals(0)){
            news.setCategory("行业新闻");
        }else if(news.getCategory().equals(1)){
            news.setCategory("公司新闻");
        }else{
            news.setCategory("公司通知");
        }
        news.setUpdateUser(user.getUserName());
        newsMapper.updateByPrimaryKey(news);
    }

    public List<News> listPage(Map<String, Object> map){
        if(map.containsKey("category")){
            String category = map.get("category").toString();
            if(category.equals("0")){
                map.put("category","行业新闻");
            }else if(category.equals("1")){
                map.put("category","公司新闻");
            }else if(category.equals("2")){
                map.put("category","公司活动");
            }else {
                map.put("category",null);
            }
            return newsMapper.listPage(map);
        }

        return newsMapper.listPage(map);
    }

    public void removeNews(Integer id) {
        newsMapper.deleteByPrimaryKey(id);
    }

    public Integer selectCount(Map<String, Object> map){
        return newsMapper.selectCount(map);
    }

    public News queryOneNews(News news) {
        return newsMapper.selectByPrimaryKey(news.getId());
    }
}
