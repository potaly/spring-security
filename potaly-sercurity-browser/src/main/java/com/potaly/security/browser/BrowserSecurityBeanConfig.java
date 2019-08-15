package com.potaly.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.potaly.security.core.properties.SecurityProperties;

/**
 * @author wang.qiang
 * @date 2019年8月14日
 */
@Configuration
public class BrowserSecurityBeanConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    @Autowired
	private SecurityProperties securityProperties;
    
    @Autowired
    private AuthenticationSuccessHandler iPotalyAuthenticationSuccessHandler;
	@Autowired
    private AuthenticationFailureHandler iPotalyAuthenticationFailureHandler;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage("/authentication/require")  //跳到controller
		.loginProcessingUrl("/authentication/form")
		.successHandler(iPotalyAuthenticationSuccessHandler)
		.failureHandler(iPotalyAuthenticationFailureHandler)
		 .and()
		 .authorizeRequests()
		 .antMatchers("/authentication/require"
				 ,securityProperties.getBrowser().getLoginPage()
				 ,"/code/image").permitAll()
		 .anyRequest()
		 .authenticated()
		 .and().csrf().disable();

	}
}
