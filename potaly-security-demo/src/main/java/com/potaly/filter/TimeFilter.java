package com.potaly.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 
 * @author potaly
 * @date 2019年8月13日
 */
public class TimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("timeFilter is init");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter is start"); 
		long start = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("过滤器耗时:"+(new Date().getTime()-start));
		System.out.println("time filter is end");
	}

	@Override
	public void destroy() {
	 System.out.println("timefilter is destroy");

	}

}
