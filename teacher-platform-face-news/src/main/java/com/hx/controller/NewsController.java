package com.hx.controller;


import com.hx.common.utils.BeanUtils;
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
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping("/query_by_page")
    public R queryNews(@RequestBody(required = false) Map<String, Object> params){
        News news = new News();
        Integer count = newsService.selectCount(params);
        return R.ok(newsService.listPage(params),count);
    }
    @RequestMapping("/query")
    public R queryOneNews( @RequestBody News news){
        return R.ok(newsService.listPage(BeanUtils.beanToMap(news)));
    }
}
