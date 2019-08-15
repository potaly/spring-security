package com.potaly.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.potaly.filter.TimeFilter;
import com.potaly.web.intercepter.TimeIntercepter;
/**
 * 
 * @author potaly
 * @date 2019年8月13日
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	
	@Autowired
	private TimeIntercepter timeIntercepter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(timeIntercepter);
	}
	
	
	//@Bean
	public FilterRegistrationBean setFilter() {
		FilterRegistrationBean re = new FilterRegistrationBean();
		TimeFilter time = new TimeFilter();
		re.setFilter(time);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		re.setUrlPatterns(urls);
		return re;
	}
	
}
