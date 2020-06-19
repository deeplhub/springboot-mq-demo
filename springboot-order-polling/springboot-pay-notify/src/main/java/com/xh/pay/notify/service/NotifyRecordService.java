package com.xh.pay.notify.service;

import com.xh.pay.notify.entity.NotifyRecord;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public interface NotifyRecordService {

    List<NotifyRecord> queryNotifyRecordList();

    void updateNotifyRecord(Long id, Integer notifyTimes, String status);

    NotifyRecord getNotifyRecordById(Long id);

    void saveNotifyRecord(NotifyRecord notifyRecord);

    NotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo, String merchantOrderNo, String notifyType);
}
