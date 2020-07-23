package com.zheliban.miaosha.rabbitmq;

import com.zheliban.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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

    public void sendMiaoshaMsg(MiaoshaMessage miaoshaMessage) {
        String msg = RedisService.beanToString(miaoshaMessage);
        logger.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE,msg);
    }


    public void send(Object message){
        String msg = RedisService.beanToString(message);
        logger.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE,message);
    }
    public void sendTopic(Object message){
        String msg = RedisService.beanToString(message);
        logger.info("send topic message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key1",msg+"1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key2",msg+"2");
    }
    public void sendFanout(Object message){
        String msg = RedisService.beanToString(message);
        logger.info("send fanout message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",msg+"1");
    }

    public void sendHeader(Object message){
        String msg = RedisService.beanToString(message);
        logger.info("send header message:"+msg);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1","value1");
        properties.setHeader("header2","value2");
        Message obj = new Message(msg.getBytes(),properties);
        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE,"",obj);
    }


}
