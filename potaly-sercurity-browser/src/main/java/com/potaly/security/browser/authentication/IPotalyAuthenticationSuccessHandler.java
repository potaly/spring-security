package com.potaly.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potaly.security.core.enums.LoginType;
import com.potaly.security.core.properties.SecurityProperties;

/**
 * @author wang.qiang
 * @date 2019年8月14日
 */
@Component("iPotalyAuthenticationSuccessHandler")
//public class IPotalyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class IPotalyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

//Authentication  ==>封装认证信息 包括认证请求的ip,session
	private Logger logger =LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
			logger.info("登录成功");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else{
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}

}
