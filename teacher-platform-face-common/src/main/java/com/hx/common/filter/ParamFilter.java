package com.hx.common.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*", filterName = "ParamFilter")
public class ParamFilter implements Filter {
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
			Arrays.asList("/css", "/js", "/editor-app","/logout", "/fonts","/img","/index","/sys/menu")));

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) request;
		String url = req.getServletPath().trim();
		boolean allowedPath = false;
		for (String s : ALLOWED_PATHS) {
			boolean isStartWith = url.startsWith(s);//
			if (isStartWith){ //包含
				allowedPath = true;
				break;
			}
		}
		if (allowedPath){//不需要过滤的url，这里可以使用一个配置文件配置这些url，项目启动时读入内存一个map中，然后在这里进行判断
			//我定义的是urlFilterMap，然后在这里urlFilterMap.containsValue(url)进行判断
			chain.doFilter(req, response);
		}else{
			ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(req);
			chain.doFilter(requestWrapper, response);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}