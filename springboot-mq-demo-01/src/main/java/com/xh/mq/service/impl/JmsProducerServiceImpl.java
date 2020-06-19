package com.xh.mq.service.impl;

import com.xh.mq.service.JmsProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Title: 消息提供者
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
@Service
public class JmsProducerServiceImpl implements JmsProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessageToTopic(String destination, String message) {
        jmsTemplate.convertAndSend(new ActiveMQTopic(destination), message);
    }

    @Override
    public void sendMessageToQueue(String destination, String message) {
        jmsTemplate.convertAndSend(new ActiveMQQueue(destination), message);
    }
}
