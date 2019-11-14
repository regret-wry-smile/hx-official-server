package com.hx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
//@EnableDistributedTransaction //分布式
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@ServletComponentScan  //开启servlet、filter注解扫描
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableCaching
@EnableScheduling
//@EnableEurekaClient
//@EnableFeignClients
public class TeacherPlatformFaceApplication extends SpringBootServletInitializer {//设置tomcat启动配置：继承SpringBootServletInitializer，重写configure方法，将springboot的启动类设置进去。

    /**
     * 需要把web项目打成war包部署到外部tomcat运行时需要改变启动方式
     */
    @Override  //重写configure方法，把springboot的入口给它
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TeacherPlatformFaceApplication.class);
    }

    public static void main(String[] args) {
        /*SpringApplication application = new SpringApplication(PlatformApplication.class);
        *//*
         * Banner.Mode.OFF:关闭;
         * Banner.Mode.CONSOLE:控制台输出，默认方式;
         * Banner.Mode.LOG:日志输出方式;
         *//*
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);*/
        SpringApplication.run(TeacherPlatformFaceApplication.class, args);
        System.out.println("------    teacher-platform-face启动成功    ------\n");
    }

}
