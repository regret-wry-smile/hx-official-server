package com.hx.common.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Configuration  //需启用cas则开启该注解
public class CasConfig {
    @Autowired
    private SpringCasAutoconfig casAutoconfig;

    private static boolean casEnabled  = true;

    public CasConfig() {}

    @Bean
    public SpringCasAutoconfig getCasAutoconfig(){
        return new SpringCasAutoconfig();
    }

    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new AuthenticationFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
//        initParameters.put("casServerLoginUrl", "http://127.0.0.1:9080/cas/login");
        initParameters.put("casServerLoginUrl", casAutoconfig .getCasServerLoginUrl());
//        initParameters.put("serverName", "http://127.0.0.1:8888");
        initParameters.put("serverName", casAutoconfig.getServerName());
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(2);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
    @Bean
    public FilterRegistrationBean singleSignOutFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
//        filterRegistrationBean.addInitParameter("casServerUrlPrefix","http://127.0.0.1:9080/cas");
        filterRegistrationBean.addInitParameter("casServerUrlPrefix",casAutoconfig.getCasServerUrlPrefix());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        filterRegistrationBean.setFilter(singleSignOutFilter);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
       servletListenerRegistrationBean.setOrder(1);
       return servletListenerRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean ValidationFilterRegistrationBean(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
//        initParameters.put("casServerUrlPrefix", "http://127.0.0.1:9080/cas");
        initParameters.put("casServerUrlPrefix", casAutoconfig.getCasServerUrlPrefix());
//        initParameters.put("serverName", "http://127.0.0.1:8888");
        initParameters.put("serverName", casAutoconfig.getServerName());
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(1);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    @Bean
    public FilterRegistrationBean casHttpServletRequestWrapperFilter(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new HttpServletRequestWrapperFilter());
        authenticationFilter.setOrder(3);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    @Bean
    public FilterRegistrationBean casAssertionThreadLocalFilter(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new AssertionThreadLocalFilter());
        authenticationFilter.setOrder(4);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
}
