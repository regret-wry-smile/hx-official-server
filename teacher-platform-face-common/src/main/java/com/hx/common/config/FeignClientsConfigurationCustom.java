//package com.hx.common.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
///**
// * 需求, Feign调用时要带cookie过去
// * 当未引入Hystrix熔断策略时 开启此类  否则开启FeignHystrixConcurrencyStrategy类
// */
//@Configuration
//public class FeignClientsConfigurationCustom implements RequestInterceptor {
//
//  @Override
//  public void apply(RequestTemplate template) {
//
//    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//    if (requestAttributes == null) {
//      return;
//    }
//
//    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//    Enumeration<String> headerNames = request.getHeaderNames();
//    if (headerNames != null) {
//      while (headerNames.hasMoreElements()) {
//        String name = headerNames.nextElement();
//        Enumeration<String> values = request.getHeaders(name);
//        while (values.hasMoreElements()) {
//          String value = values.nextElement();
//          template.header(name, value);
//        }
//      }
//    }
//
//  }
//
//}