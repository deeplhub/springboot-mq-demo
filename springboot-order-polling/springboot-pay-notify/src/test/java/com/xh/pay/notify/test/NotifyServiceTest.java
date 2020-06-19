package com.xh.pay.notify.test;

import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NotifyServiceTest {

    @Resource
    private NotifyRecordService notifyRecordService;

    @Test
    public void query() {
        List<NotifyRecord> list = notifyRecordService.queryNotifyRecordList();
        System.out.println(list.size());
    }

    @Test
    public void getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType() {
        NotifyRecord notifyRecord = notifyRecordService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType("1234567", "100001", "商户通知");
        System.out.println(notifyRecord.toString());
    }

    @Test
    public void updateNotifyRecord() {
        notifyRecordService.updateNotifyRecord(1L, 1, "商户通知22222222");
    }
}
