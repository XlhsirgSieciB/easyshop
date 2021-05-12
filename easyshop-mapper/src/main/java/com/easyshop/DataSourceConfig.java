package com.easyshop;

import javax.sql.DataSource;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

/*
 * SpringBootConfiguration项目配置的核心注解
 */
@SpringBootConfiguration
@PropertySource(value= {"classpath:jdbc.properties"},ignoreResourceNotFound=true)
public class DataSourceConfig {

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.username}")
	// 这里不要在properties中设置key值为username
	private String username;

	@Value("${jdbc.password}")
	private String password;

	// 创建一个数据源对象,并放入Spring容器中
	@Bean
	public DataSource dataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(url);
		ds.setDriverClassName(driverClassName);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
}
