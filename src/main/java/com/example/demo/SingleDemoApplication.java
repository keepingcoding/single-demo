package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zzs
 */
//@MapperScan(basePackages = "com.example.demo.mybatis.dao")
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        RabbitAutoConfiguration.class
})
@ImportResource(locations = {"classpath*:applicationContext.xml"})
@PropertySource(value = {"classpath:config.properties"}, ignoreResourceNotFound = true)
public class SingleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleDemoApplication.class, args);
    }
}
