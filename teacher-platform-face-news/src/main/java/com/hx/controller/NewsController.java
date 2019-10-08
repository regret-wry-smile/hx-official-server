package com.hx.controller;


import com.hx.domain.R;
import com.hx.pojo.News;
import com.hx.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backnews")
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping("/query_by_page")
    public List<News> queryNews(@RequestBody(required = false) Map<String, Object> params){
        List<News> newsList = newsService.listPage(params);
        return newsList;
    }
    @RequestMapping("/query")
    public List<News> queryOneNews(@RequestBody(required = false) Map<String, Object> params){
        List<News> newsList = newsService.listPage(params);
        return newsList;
    }
}
