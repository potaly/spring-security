package com.potaly.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.potaly.security.core.properties.SecurityProperties;

/**
 * @author wang.qiang
 * @date 2019年8月14日
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
