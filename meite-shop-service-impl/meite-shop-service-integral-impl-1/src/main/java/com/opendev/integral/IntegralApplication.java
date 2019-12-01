package com.opendev.integral;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 积分服务启动
 * 
 * 
 */
@SpringBootApplication
@MapperScan(basePackages = "com.opendev.integral.mapper")
public class IntegralApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegralApplication.class, args);
	}

}
