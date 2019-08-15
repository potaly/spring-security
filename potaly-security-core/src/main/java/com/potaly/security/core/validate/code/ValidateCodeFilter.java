package com.potaly.security.core.validate.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 在security的过滤器链上加入自己定义的过滤器，用来验证验证码，加在UsernamePasswordAuthenticationFilter
 * 之前
 * @author wang.qiang
 * @date 2019年8月15日
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}