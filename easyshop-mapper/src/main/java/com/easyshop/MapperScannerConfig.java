package com.easyshop;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@AutoConfigureAfter(MybatisConfig.class)  // 保证在MyBatisConfig实例化之后再实例化该类
public class MapperScannerConfig {

	// mapper接口的扫描器
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.easyshop.mapper");
		return mapperScannerConfigurer;
	}

}
