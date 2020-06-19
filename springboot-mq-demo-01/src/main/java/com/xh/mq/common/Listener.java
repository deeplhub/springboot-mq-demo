package com.xh.mq.common;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Title: 消费者监听
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
@Component
public class Listener {

    //监听名为queue_msg Queue的消息，监听类型由queueListenerContainer决定
    @JmsListener(destination = "active.queue.msg",containerFactory = "queueListener")
    public void consumeQueue1(String msg){
        System.out.println("queue_msg1获得的消息是：" + msg);
    }
    //监听名为queue_msg Queue的消息，监听类型由queueListenerContainer决定
    @JmsListener(destination = "active.queue.msg",containerFactory = "queueListener")
    public void consumeQueue2(String msg){
        System.out.println("queue_msg2获得的消息是：" + msg);
    }


//    //监听名为topic_msg Topic的消息，监听类型由topicListenerContainer决定
//    @JmsListener(destination = "topic_msg",containerFactory = "topicListenerContainer")
//    public void consumeTopic1(String msg){
//        System.out.println("topic_msg1获得的消息是" + msg);
//    }
//    //监听名为topic_msg Topic的消息，监听类型由topicListenerContainer决定
//    @JmsListener(destination = "topic_msg",containerFactory = "topicListenerContainer")
//    public void consumeTopic2(String msg){
//        System.out.println("topic_msg2获得的消息是" + msg);
//    }
}
