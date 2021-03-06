package com.atguigu.gulimall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 远程调用优惠券服务的步骤：
 * 1、引入open-feign（远程调用别人能力）
 * 2、编写一个接口，告诉SpringCloud这个接口需要调用远程服务
 * (1)声明接口的每一个方法都是调用哪个远程服务的那个请求
 * 3、开启远程调用功能
 */
@EnableFeignClients("com.atguigu.gulimall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallMemberApplication.class, args);
    }

}
