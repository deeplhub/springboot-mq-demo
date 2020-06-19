package com.xh.pay.notify.dao.rep;

import com.xh.pay.notify.entity.NotifyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public interface NotifyRecordRepDao extends JpaRepository<NotifyRecord, Long> {

    @Query("SELECT nr FROM NotifyRecord nr WHERE nr.status IN ('101', '102', '200', '201') AND nr.notifyTimes IN (0, 1, 2, 3, 4) ")
    List<NotifyRecord> queryNotifyRecordList();

    @Modifying
    @Query("UPDATE NotifyRecord nr SET nr.notifyTimes=?2, nr.status=?3 WHERE nr.id=?1")
    void updateNotifyRecord(Long id, Integer notifyTimes, String status);

    @Query("SELECT nr FROM NotifyRecord nr WHERE nr.merchantNo=?1 AND nr.merchantOrderNo=?2 AND nr.notifyType=?3")
    NotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo, String merchantOrderNo, String notifyType);
}
