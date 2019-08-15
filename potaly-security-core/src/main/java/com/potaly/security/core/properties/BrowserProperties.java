/**
 * 
 */
package com.potaly.security.core.properties;

import com.potaly.security.core.enums.LoginType;

/**
 * 
 * @author potaly
 * @date 2019年8月14日
 */
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String signUpUrl = "/imooc-signUp.html";//登录页面
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	 
	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}
	
}
