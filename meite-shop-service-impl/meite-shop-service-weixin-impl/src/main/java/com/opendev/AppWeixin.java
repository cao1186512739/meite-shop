package com.opendev;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.Async;

@Async
@EnableEurekaClient
@EnableSwagger2Doc  //开启swagger注解
@SpringBootApplication
public class AppWeixin {
    public static void main(String[] args) {
        SpringApplication.run(AppWeixin.class, args);
    }
}
