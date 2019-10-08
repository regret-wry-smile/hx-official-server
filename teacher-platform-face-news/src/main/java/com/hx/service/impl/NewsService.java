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
        news.setCreateUser(user.getUserName());
        newsMapper.insert(news);
    }

    public void deleteNews(List<Integer> ids) {
        newsMapper.deleteByIds(ids);
    }

    public void updateNews(News news) throws ParseException{
        HxUser user = ShiroUtils.getUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(new Date());
        Date date = sf.parse(s);
        news.setCreateTime(date);
        news.setCreateUser(user.getUserName());
        newsMapper.updateByPrimaryKey(news);
    }

    public List<News> listPage(Map<String, Object> map){
        return newsMapper.listPage(map);
    }

    public void removeNews(Integer id) {
        newsMapper.deleteByPrimaryKey(id);
    }
}
