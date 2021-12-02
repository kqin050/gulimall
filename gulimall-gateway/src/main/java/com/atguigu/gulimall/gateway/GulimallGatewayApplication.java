package com.atguigu.gulimall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1、开启服务注册发现
 * 配置nacos注册中心地址
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class} )//因为pom文件里面有mybatis plus相关文件，但是这个服务没用用到数据库，所以排除掉数据源相关
public class GulimallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallGatewayApplication.class, args);
    }

}
