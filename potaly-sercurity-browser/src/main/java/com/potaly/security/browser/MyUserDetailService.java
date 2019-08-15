package com.potaly.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author wang.qiang
 * @date 2019年8月14日
 */
@Component
public class MyUserDetailService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String passw = passwordEncoder.encode("123456");
		logger.info("数据库密码是：{}",passw);
		return new User(username,passw,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
