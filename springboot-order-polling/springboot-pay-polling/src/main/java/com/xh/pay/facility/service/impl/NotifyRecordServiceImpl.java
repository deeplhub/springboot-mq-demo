package com.xh.pay.facility.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xh.pay.facility.model.NotifyRecord;
import com.xh.pay.facility.service.NotifyRecordService;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
@Service
public class NotifyRecordServiceImpl implements NotifyRecordService {

    private static final Logger LOG = LoggerFactory.getLogger(NotifyRecordServiceImpl.class);
    @Resource
    private JmsTemplate jmsTemplate;

    @Override
    public void notifySend(String notifyUrl, String merchantOrderNo, String merchantNo) {
        NotifyRecord notifyRecord = new NotifyRecord();
        notifyRecord.setNotifyTimes(0);
        notifyRecord.setLimitNotifyTimes(5);
        notifyRecord.setStatus("通知记录已创建");
        notifyRecord.setUrl(notifyUrl);
        notifyRecord.setMerchantOrderNo(merchantOrderNo);
        notifyRecord.setMerchantNo(merchantNo);
        notifyRecord.setNotifyType("商户通知");

        Object toJSON = JSONObject.toJSON(notifyRecord);
        String str = toJSON.toString();

        LOG.info("推送到回调消息队列中");
        jmsTemplate.convertAndSend(new ActiveMQQueue("active.notify.queue.msg"), str);
    }
}
