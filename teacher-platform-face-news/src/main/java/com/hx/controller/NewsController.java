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
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping("/add")
    public R saveNews(@RequestBody News news){
        newsService.saveNews(news);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R deleteNews(@RequestBody Integer[]  ids){
        newsService.deleteNews(Arrays.asList(ids));
        return R.ok();
    }
    @RequestMapping("/update")
    public R updateNews(@RequestBody News news){
        newsService.updateNews(news);
        return R.ok();
    }

}
