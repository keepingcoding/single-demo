package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class,
        RabbitAutoConfiguration.class})
@ImportResource(locations = {"classpath:applicationContext.xml"})
@PropertySource(value = {"classpath:config.properties"}, ignoreResourceNotFound = true)
public class SingleDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SingleDemoApplication.class, args);

        AmqpTemplate directMQTemplate = context.getBean("directMQTemplate", AmqpTemplate.class);
        AmqpTemplate topicMQTemplate = context.getBean("topicMQTemplate", AmqpTemplate.class);

        directMQTemplate.convertAndSend("direct.message.1",JSON.toJSONString("hello, this is message from rabbit"));
        directMQTemplate.convertAndSend("direct.message.2",JSON.toJSONString("hello, this is message from rabbit"));


        topicMQTemplate.convertAndSend("topic.message.1",JSON.toJSONString("hello, this is message from rabbit"));
        topicMQTemplate.convertAndSend("topic.message.2.test.123",JSON.toJSONString("hello, this is message from rabbit"));
        topicMQTemplate.convertAndSend("topic.message.3.test",JSON.toJSONString("hello, this is message from rabbit"));
    }
}
