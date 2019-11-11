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
    public R queryNews(@RequestBody(required = false) Map<String, Object> params) {
        if (params.containsKey("category")) {
            String category = params.get("category").toString();
            if (category.equals("0")) {
                params.put("category", "行业新闻");
            } else if (category.equals("1")) {
                params.put("category", "公司新闻");
            } else if (category.equals("2")) {
                params.put("category", "公司活动");
            } else {
                params.put("category", "");
            }
        }
        return R.ok(newsService.listPage(params), newsService.selectCount(params));
    }

    @RequestMapping("/query")
    public R queryOneNews( @RequestBody News news){
        return R.ok(newsService.queryOneNews(news));
    }
}
