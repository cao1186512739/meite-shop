package com.opendev.pay;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxm
 * @create 2019-05-29 19:19
 */
@EnableSwagger2Doc
@SpringBootApplication
@EnableEurekaClient
//@EnableApolloConfig
//@MapperScan(basePackages = {"com.opendev.pay.dao"})
@EnableFeignClients(basePackages = "com.opendev.api")  //开启FeignClient支持
@ComponentScan(basePackages={"com.opendev.api","com.opendev.pay","com.opendev"})//扫描接口
public class PayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class,args);
    }
}
