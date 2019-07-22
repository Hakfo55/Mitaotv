package com.mountain.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统环境变量
 */
@Component
public class ApplicationConfig {

	@Value("${spring.profiles.active}")
	public String env;//系统环境  test 测试  product 生产
	@Value("${api.host}")
	public String apiHost;//本机访问地址
}
