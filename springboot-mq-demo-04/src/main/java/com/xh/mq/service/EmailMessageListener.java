package com.xh.mq.service;

import com.alibaba.fastjson.JSON;
import com.xh.mq.common.constant.Cons;
import com.xh.mq.model.User;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
@Component
public class EmailMessageListener {

    //监听名为queue_msg Queue的消息，监听类型由queueListenerContainer决定
//    @JmsListener(destination = "active.queue.msg", containerFactory = "queueListener")
    @JmsListener(destination = "active.register.topic.msg", containerFactory = Cons.TOPIC_LISTENER)
    public void consumeQueue1(String msg) throws InterruptedException {
        User user = JSON.parseObject(msg, User.class);

        Thread.sleep(2 * 1000);
        System.out.println("邮件发送成功:" + user.toString());
    }
}
