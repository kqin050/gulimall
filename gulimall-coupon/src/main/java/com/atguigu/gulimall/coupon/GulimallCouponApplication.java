package com.atguigu.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 如何使用Nacos作为配置中心统一管理配置：
 * 1。引入依赖
 *  <dependency>
 *      <groupId>com.alibaba.cloud</groupId>
 *      <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *  </dependency>
 *  2.创建一个bootstrap.properties
 *  spring.application.name=gulimall-coupon
 *  spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 *  3.给配置中心添加一个默认叫 数据集（Data Id）gulimall-coupon.properties
 *  4.给应用名.properties添加任何配置
 *  5.动态获取配置
 *       @RefreshScope 动态获取并刷新配置
 *      @Value获取配置的值（"${配置项的名}"）
 *      如果配置中心和当前应用配置的文件中都配置了相同的项，优先使用配置中心
 *
 * 2、nacos的细节
 * （1）命名空间：配置隔离
 *  默认：public（保留空间）：默认新增所有配置都在public空间
 *      1、开发、测试、生产:利用命名空间做环境隔离
 *      注意：在bootstrap.properties配置文件中，需要注明使用哪个命名空间下的配置
 *      spring.cloud.nacos.config.namespace=8bbad18e-2de7-4e18-b436-89d048187063
 *      2、基于每一个微服务之间，互相隔离配置，每一个微服务都创建自己的命名空间，只加载自己命名空间下的配置
 *       spring.cloud.nacos.config.namespace= 1e547d57-c552-4f0b-9735-326b46c78dcf
 * （2）配置集:所有的配置集合
 * （3）配置集ID：类似配置文件名
 * (4) 配置分组：
 *      默认所有的配置集都属于：DEFAULT_GROUP；
 *      双11配置、618配置
 *  每个微服务创建自己的命名空间，使用配置分组区分环境，dev,test,prop
 *
 *  同时加载多个配置集：
 *  1）微服务任何配置信息，任何文件都可以方在配置中心中
 *  2）、只需要在bootsrap.properties说明中加载配置中心的那些文件即可
 *  3）、@Value、@ConfigurationProperties
 *  之前SpringBoot任何方式从配置文件中获取值，都能使用，配置中心有的，优先使用配置中心中的
 */

@EnableDiscoveryClient//该服务就会注册到注册中心
@SpringBootApplication
public class GulimallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCouponApplication.class, args);
    }

}
