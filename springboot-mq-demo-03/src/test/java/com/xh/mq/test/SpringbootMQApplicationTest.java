package com.xh.mq.test;

import com.alibaba.fastjson.JSON;
import com.xh.mq.model.User;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMQApplicationTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void sendMessage() throws InterruptedException {
        // 注册
        User user = new User();
        user.setId(1l);
        user.setName("aaa");
        user.setPassword("pwd123");
        user.setAge(10);
        user.setSex((byte) 1);

        Thread.sleep(1 * 1000);
        System.out.println("注册成功，模拟持久化");

        String message = JSON.toJSONString(user);
        // 发送消息
        jmsTemplate.convertAndSend(new ActiveMQTopic("active.topic.msg"), message);
//        jmsTemplate.convertAndSend(new ActiveMQQueue("active.queue.msg"), message);

        System.out.println("执行完成");

//        Thread.sleep(5 * 1000);
    }


}
