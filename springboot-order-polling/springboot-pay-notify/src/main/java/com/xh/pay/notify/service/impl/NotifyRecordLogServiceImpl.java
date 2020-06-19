package com.xh.pay.notify.service.impl;

import com.xh.pay.notify.dao.rep.NotifyRecordLogRepDao;
import com.xh.pay.notify.entity.NotifyRecordLog;
import com.xh.pay.notify.service.NotifyRecordLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@Service
public class NotifyRecordLogServiceImpl implements NotifyRecordLogService {

    @Resource
    private NotifyRecordLogRepDao notifyRecordLogRepDao;

    @Transactional
    @Override
    public void saveNotifyRecordLogs(Long id, String merchantNo, String merchantOrderNo, String url, String response, int httpStatus) {
        NotifyRecordLog notifyRecordLog = new NotifyRecordLog();
        notifyRecordLog.setHttpStatus(httpStatus);
        notifyRecordLog.setMerchantNo(merchantNo);
        notifyRecordLog.setMerchantOrderNo(merchantOrderNo);
        notifyRecordLog.setNotifyId(id);
        notifyRecordLog.setRequest(url);
        notifyRecordLog.setResponse(response);
        notifyRecordLog.setCreateTime(new Date());
        notifyRecordLog.setEditTime(new Date());
        notifyRecordLogRepDao.save(notifyRecordLog);
    }
}
