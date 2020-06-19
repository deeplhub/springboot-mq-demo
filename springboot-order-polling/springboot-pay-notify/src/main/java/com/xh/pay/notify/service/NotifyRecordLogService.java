package com.xh.pay.notify.service;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public interface NotifyRecordLogService {

    void saveNotifyRecordLogs(Long id, String merchantNo, String merchantOrderNo, String url, String response, int httpStatus);

}
