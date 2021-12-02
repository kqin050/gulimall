package com.atguigu.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1、整合MyBatis-Plus
 *  *      1）、导入依赖
 *  *      <dependency>
 *  *             <groupId>com.baomidou</groupId>
 *  *             <artifactId>mybatis-plus-boot-starter</artifactId>
 *  *             <version>3.2.0</version>
 *  *      </dependency>
 *  *      2）、配置
 *  *          1、配置数据源；
 *  *              1）、导入数据库的驱动。https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-versions.html
 *  *              2）、在application.yml配置数据源相关信息
 *  *          2、配置MyBatis-Plus；
 *  *              1）、使用@MapperScan
 *  *              2）、告诉MyBatis-Plus，sql映射文件位置
 *  *
 *  * 2、逻辑删除
 *  *  1）、配置全局的逻辑删除规则（省略）
 *  *  2）、配置逻辑删除的组件Bean（省略）
 *  *  3）、给Bean加上逻辑删除注解@TableLogic
 *    3、 JSR303验证
 *    1）、给Bean添加校验注解：javax.validation.constraints,并定义自己的message提示
 *    2）、开启校验功能@Valid
 *    效果：校验错误以后，会有默认的响应
 *    3）、给校验的bean后，紧跟一个BindingResult
 *    4)、分组校验功能
 *      1）、	@NotBlank(message = "品牌名必须提交不能为空的数据",groups = {AddGroup.class,UpdateGroup.class})
 *      给校验注解标注什么情况需要进行校验
 *      2)、@Validated({AddGroup.class})
 *      3)、默认没有指定分组的校验注解@NotBlank，在分组校验的情况@Validated({AddGroup.class})下不 生效
 *    5）、自定义校验
 *      1）、编写一个自定义校验注解
 *      2）、编写一个自定义校验器
 *      3）、关联自定义校验器跟校验注解
 *
 *    4、统一的异常处理
 * @ControllerAdvice
 * 1)、
 */
@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
