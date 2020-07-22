package com.zheliban.miaosha.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/222:15 下午
 */
@Configuration
public class MQConfig {
    public static final  String QUEUE = "queue";
    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }
}
