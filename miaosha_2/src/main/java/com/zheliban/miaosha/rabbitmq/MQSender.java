package com.zheliban.miaosha.rabbitmq;

import com.zheliban.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/222:13 下午
 */
@Service
public class MQSender {
    private static Logger logger = LoggerFactory.getLogger(MQSender.class);
    @Autowired
    AmqpTemplate amqpTemplate;
    public void send(Object message){
        String msg = RedisService.beanToString(message);
        logger.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE,message);
    }
}
