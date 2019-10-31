package com.hx.common.redis.shiro;

import com.alibaba.fastjson.JSON;
import com.hx.domain.R;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        res.setStatus(HttpServletResponse.SC_OK);
        res.setHeader("Content-Type", "application/json;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        res.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        PrintWriter writer = res.getWriter();
        writer.write(JSON.toJSONString(R.error(R.NOT_LOGIN_CODE, "未登陆")));
        writer.close();
        return false;
    }
}