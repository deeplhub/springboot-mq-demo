package com.xh.pay.facility.listener;

import com.alibaba.fastjson.JSON;
import com.xh.pay.facility.config.Cons;
import com.xh.pay.facility.core.NotifyQueue;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@Component
public class NotifyMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(NotifyMessageListener.class);

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private NotifyRecordService notifyRecordService;

    @JmsListener(destination = "active.notify.queue.msg", containerFactory = Cons.QUEUE_LISTENER)
    public void onMessage(String message) throws InterruptedException {
        LOG.info("== receive message:" + message);
        try {
            NotifyRecord notifyRecord = JSON.parseObject(message, NotifyRecord.class);
            if (notifyRecord == null) {
                return;
            }
            // log.info("notifyParam:" + notifyParam);
            notifyRecord.setStatus("通知记录已创建");
            notifyRecord.setCreateTime(new Date());
            notifyRecord.setLastNotifyTime(new Date());

            if (notifyRecord.getId() != null && "".equals(notifyRecord.getId())) {
                NotifyRecord notifyRecordById = notifyRecordService.getNotifyRecordById(notifyRecord.getId());
                if (notifyRecordById != null) {
                    return;
                }
            }
            while (notifyRecordService == null) {
                Thread.currentThread().sleep(1000); // 主动休眠，防止类Spring 未加载完成，监听服务就开启监听出现空指针异常
            }

            try {
                // 将获取到的通知先保存到数据库中
                notifyRecordService.saveNotifyRecord(notifyRecord);
                notifyRecord = notifyRecordService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(notifyRecord.getMerchantNo(), notifyRecord.getMerchantOrderNo(), notifyRecord.getNotifyType());

                // 添加到通知队列
                notifyQueue.addElementToList(notifyRecord);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
