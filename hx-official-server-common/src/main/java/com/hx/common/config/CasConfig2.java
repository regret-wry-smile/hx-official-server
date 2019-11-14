package com.hx.common.config;/*
package com.hx.common.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.productweb.servlet.FilterRegistrationBean;
import org.springframework.boot.productweb.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.productweb.authentication.logout.LogoutFilter;
import org.springframework.security.productweb.authentication.logout.SecurityContextLogoutHandler;


*/
/**
 * 单点登录配置类
 *//*

@Configuration
public class CasConfig {

    @Autowired
    private CasAutoconfig casAutoconfig;

    private static boolean casEnabled  = true;

    public CasConfig() {}

    @Bean
    public CasAutoconfig getCasAutoconfig(){
        return new CasAutoconfig();
    }

    */
/**
     * 用于实现单点登出功能
     *//*

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
        listener.setEnabled(casEnabled);
        listener.setListener(new SingleSignOutHttpSessionListener());
        listener.setOrder(1);
        return listener ;
    }

    */
/**
     * 该过滤器用于实现单点登出功能，单点退出配置，一定要放在其他filter之前
     *//*

    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new SingleSignOutFilter());
        filterRegistration.setEnabled(casEnabled);
        filterRegistration.addUrlPatterns("/*" );
        filterRegistration.addInitParameter("casServerUrlPrefix" , casAutoconfig .getCasServerUrlPrefix());
        filterRegistration.setOrder(2);
        return filterRegistration ;
    }

    */
/**
     * 该过滤器负责用户的认证工作
     *//*

    @Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new AuthenticationFilter());
        filterRegistration.setEnabled(casEnabled);
        filterRegistration.addUrlPatterns("/*" );
        filterRegistration.addInitParameter("casServerLoginUrl" , casAutoconfig .getCasServerLoginUrl());
        filterRegistration.addInitParameter("casPassUrl" , casAutoconfig.getCasPassUrl());
        filterRegistration.addInitParameter("serverName" , casAutoconfig.getServerName());
        filterRegistration.addInitParameter("renew" , casAutoconfig.isRenew() ? "true" :"false" );
        filterRegistration.addInitParameter("gateway" , casAutoconfig.isGateway() ? "true" :"false" );
        filterRegistration.addInitParameter("casResource" , casAutoconfig.getResource());
        filterRegistration.setOrder(3);
        return filterRegistration ;
    }

    */
/**
     * 该过滤器负责对Ticket的校验工作
     *//*

    @Bean
    public FilterRegistrationBean cas20ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        Cas20ProxyReceivingTicketValidationFilter cas20ProxyReceivingTicketValidationFilter = new Cas20ProxyReceivingTicketValidationFilter();
        filterRegistration.setFilter(cas20ProxyReceivingTicketValidationFilter );
        filterRegistration.setEnabled(casEnabled);
        filterRegistration.addUrlPatterns("/*" );
        filterRegistration.addInitParameter("casServerUrlPrefix" , casAutoconfig .getCasServerUrlPrefix());
        filterRegistration.addInitParameter("serverName" , casAutoconfig.getServerName());
        filterRegistration.addInitParameter("useSession" , casAutoconfig.isUseSession() ? "true" :"false" );
        filterRegistration.addInitParameter("exceptionOnValidationFailure" , casAutoconfig.isExceptionOnValidationFailure() ? "true":"false" );
        filterRegistration.addInitParameter("redirectAfterValidation" , casAutoconfig .isRedirectAfterValidation() ? "true":"false" );
        filterRegistration.setOrder(4);
        return filterRegistration ;
    }

    */
/**
     * 该过滤器对HttpServletRequest请求包装， 可通过HttpServletRequest的getRemoteUser()方法获得登录用户的登录名
     *
     *//*

    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilter() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
        filterRegistration.setEnabled(true);
        if(casAutoconfig.getRequestWrapperFilters().size()>0)
            filterRegistration.setUrlPatterns(casAutoconfig.getRequestWrapperFilters());
        else
            filterRegistration.addUrlPatterns("/*");
        filterRegistration.setOrder(6);
        return filterRegistration;
    }

    */
/**
     * 该过滤器使得可以通过org.jasig.cas.client.util.AssertionHolder来获取用户的登录名。
     * 比如AssertionHolder.getAssertion().getPrincipal().getName()。
     * 这个类把Assertion信息放在ThreadLocal变量中，这样应用程序不在web层也能够获取到当前登录信息
     *//*

    @Bean
    public FilterRegistrationBean assertionThreadLocalFilter() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new AssertionThreadLocalFilter());
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*" );
        filterRegistration.setOrder(5);
        return filterRegistration ;
    }

    */
/**
     * 登录后的Filter(不是必须的)
     *//*

    //LoginValidationFilter是自定义登录后业务处理过滤器，将loginValidationFilter对象放到括号里可以让Spring自动注入
    */
/*@Bean
    public FilterRegistrationBean loginValidationFilter (LoginValidationFilter loginValidationFilter ) {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(loginValidationFilter );
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/page/*" );
        filterRegistration.addInitParameter("casPassUrl" , casAutoconfig.getCasPassUrl());
        filterRegistration.addInitParameter("casResource" , casAutoconfig.getResource());
        filterRegistration.setOrder(6);
        return filterRegistration ;
    }*//*

}
*/
