package com.xh.mq.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Title: 队列 - 消息生产者
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
@Service
public class QueueProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    // 发送消息
    public void sendMessage(String destination, String message) {
        System.out.println("QueueProducerService发送消息：" + message);
        jmsTemplate.convertAndSend(new ActiveMQQueue(destination), message);
    }
}
