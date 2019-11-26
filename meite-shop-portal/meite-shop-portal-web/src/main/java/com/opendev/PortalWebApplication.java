package com.opendev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 
 * 
 * PortalWeb
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@EnableFeignClients(basePackages="com.opendev.member")  //开启FeignClient支持
@SpringBootApplication
@EnableEurekaClient
//@EnableApolloConfig
@EnableRedisHttpSession
@ComponentScan(basePackages={"com.opendev.member","com.opendev","com.opendev.common","com.xxl.sso"})//扫描接口
public class PortalWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(PortalWebApplication.class, args);
	}
}
