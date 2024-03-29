package com.potaly.security.core.validate.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.potaly.security.core.properties.SecurityProperties;

/**
 * 在security的过滤器链上加入自己定义的过滤器，用来验证验证码，加在UsernamePasswordAuthenticationFilter
 * 之前
 * @author wang.qiang
 * @date 2019年8月15日
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

	
	/**
	 * 验证码校验失败处理器
	 */
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	/**
	 * 系统配置信息
	 */
	@Autowired
	private SecurityProperties securityProperties;
	
	private SessionStrategy sessionStrategy  = new HttpSessionSessionStrategy();
	/**
	 * 系统中的校验码处理器
	 */
	//@Autowired
	//private ValidateCodeProcessorHolder validateCodeProcessorHolder;
  

	@Override
	protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		if(StringUtils.equals("/authentication/form", request.getRequestURI())&&
				StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException exception) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
				return ;

			}
			
		}
		filterChain.doFilter(request, response);
	}
	public void validate(ServletWebRequest request) {

		ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
		 
		String codeInRequest;
		String processorType="imageCode";
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
					processorType);
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(processorType + "验证码不存在");
		}

		if (codeInSession.isExpried()) {
			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException(processorType + "验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}
	/**
	 * @return the authenticationFailureHandler
	 */
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}
	/**
	 * @param authenticationFailureHandler the authenticationFailureHandler to set
	 */
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	
}
