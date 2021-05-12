package com.easyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 启动核心程序类
 */
@SpringBootApplication
public class MyApplication {
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MyApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);//关闭banner
		app.run(args);//启动
	}
}
