package com.xh.pay.facility.service;

/**
 * Title: 回调通知
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
public interface NotifyRecordService {

    /**
     * 发送回调通知
     * @param notifyUrl
     * @param merchantOrderNo
     * @param merchantNo
     */
    void notifySend(String notifyUrl, String merchantOrderNo, String merchantNo);
}
