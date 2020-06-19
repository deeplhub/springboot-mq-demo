package com.xh.mq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Title: 队列 - 消息消费者
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
@Component
public class ConsumerMessageListener {

    //监听名为queue_msg Queue的消息，监听类型由queueListenerContainer决定
    @JmsListener(destination = "active.queue.msg",containerFactory = "queueListener")
    public void consumeQueue1(String msg){
        System.out.println("queue_msg1获得的消息是：" + msg);
    }
}
