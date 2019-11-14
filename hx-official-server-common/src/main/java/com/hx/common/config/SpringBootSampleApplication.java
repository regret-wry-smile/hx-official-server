package com.hx.common.config;//package com.hx.common.config;
//
//import com.hx.BootdoApplication;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.productweb.support.SpringBootServletInitializer;
//
///**
// * war包部署需要
// * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
// */
//public class SpringBootSampleApplication extends SpringBootServletInitializer{
//
//    private static final Logger logger = LoggerFactory.getLogger(SpringBootSampleApplication.class);
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(this.getClass());
//    }
//
//}