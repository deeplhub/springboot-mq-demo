package com.xh.mq.service;

/**
 * Title: 消息提供者接口
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
public interface JmsProducerService {

    /**
     * 发送消息（发布-订阅模式）
     *
     * @param destination
     * @param message
     */
    void sendMessageToTopic(String destination, String message);

    /**
     * 发送消息（点对点）
     *
     * @param destination
     * @param message
     */
    void sendMessageToQueue(String destination, String message);
}
