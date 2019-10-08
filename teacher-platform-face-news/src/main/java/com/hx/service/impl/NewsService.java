package com.hx.service.impl;

import com.hx.mapper.NewsMapper;
import com.hx.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    @Autowired
    NewsMapper newsMapper;

    public void saveNews(News news) {
        newsMapper.insert(news);
    }

    public void deleteNews(List<Integer> ids) {
        newsMapper.deleteByIds(ids);
    }

    public void updateNews(News news) {
        newsMapper.updateByPrimaryKey(news);
    }

    public List<News> listPage(Map<String, Object> map){
        return newsMapper.listPage(map);
    }

}
