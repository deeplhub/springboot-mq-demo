package com.xh.pay.notify.service.impl;

import com.xh.pay.notify.dao.rep.NotifyRecordRepDao;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@Service
public class NotifyRecordServiceImpl implements NotifyRecordService {

    @Resource
    private NotifyRecordRepDao notifyRecordRepDao;

    @Override
    public List<NotifyRecord> queryNotifyRecordList() {
        return notifyRecordRepDao.queryNotifyRecordList();
    }

    @Transactional
    @Override
    public void updateNotifyRecord(Long id, Integer notifyTimes, String status) {
        notifyRecordRepDao.updateNotifyRecord(id, notifyTimes, status);
    }

    @Override
    public NotifyRecord getNotifyRecordById(Long id) {
        return notifyRecordRepDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void saveNotifyRecord(NotifyRecord notifyRecord) {
        notifyRecordRepDao.save(notifyRecord);
    }

    @Override
    public NotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo, String merchantOrderNo, String notifyType) {
        return notifyRecordRepDao.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(merchantNo, merchantOrderNo, notifyType);
    }
}
