package com.zheliban.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/222:14 下午
 */
@Service
public class MQReceiver {
    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    /**
     * Direct模式 交换机
     * @param message
     */
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive (String message){
        logger.info("receive message:"+message);
    }
}
