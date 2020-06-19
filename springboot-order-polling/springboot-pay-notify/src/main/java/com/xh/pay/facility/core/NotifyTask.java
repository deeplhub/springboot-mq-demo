package com.xh.pay.facility.core;

import com.alibaba.fastjson.JSONObject;
import com.xh.pay.SpringBootNotifyApplication;
import com.xh.pay.notify.dto.HttpResult;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordLogService;
import com.xh.pay.notify.service.NotifyRecordService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public class NotifyTask implements Runnable, Delayed {

    private static final Log LOG = LogFactory.getLog(NotifyTask.class);

    private long executeTime;

    private NotifyRecord notifyRecord;

    private NotifyQueue notifyQueue;

    private NotifyParam notifyParam;

    private NotifyRecordService notifyRecordService = SpringBootNotifyApplication.notifyRecord;
    private NotifyRecordLogService notifyRecordLogService = SpringBootNotifyApplication.notifyRecordLog;


    public NotifyTask() {
    }

    public NotifyTask(NotifyRecord notifyRecord, NotifyQueue notifyQueue, NotifyParam notifyParam) {
        super();
        this.notifyRecord = notifyRecord;
        this.notifyQueue = notifyQueue;
        this.notifyParam = notifyParam;
        this.executeTime = getExecuteTime(notifyRecord);
    }

    private long getExecuteTime(NotifyRecord notifyRecord) {
        long lastTime = notifyRecord.getLastNotifyTime().getTime();
        Integer nextNotifyTime = notifyParam.getNotifyParams().get(notifyRecord.getNotifyTimes());
        return (nextNotifyTime == null ? 0 : nextNotifyTime * 1000) + lastTime;
    }


    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public void run() {
        // 得到当前通知对象的通知次数
        Integer notifyTimes = notifyRecord.getNotifyTimes();

        // 去通知
        try {
            LOG.info("Notify Url " + notifyRecord.getUrl() + " ;notify id:" + notifyRecord.getId() + ";notify times:" + notifyRecord.getNotifyTimes());

            //模拟请求支付宝回调响应
            HttpResult result = new HttpResult();

            notifyRecord.setNotifyTimes(notifyTimes + 1);
            String successValue = notifyParam.getSuccessValue();

            String responseMsg = "";
            Integer responseStatus = result.getStatusCode();

            // 得到返回状态，如果是200，也就是通知成功
            if ((responseStatus == 200 || responseStatus == 201 || responseStatus == 202 || responseStatus == 203 || responseStatus == 204 || responseStatus == 205 || responseStatus == 206)) {
                responseMsg = "支付成功";
                responseMsg = responseMsg.length() >= 600 ? responseMsg.substring(0, 600) : responseMsg;
                LOG.info("订单号： " + notifyRecord.getMerchantOrderNo() + " HTTP_STATUS：" + responseStatus + "请求返回信息：" + responseMsg);
                // 通知成功
                if (responseMsg.trim().equals(successValue)) {
                    notifyRecordService.updateNotifyRecord(notifyRecord.getId(), notifyRecord.getNotifyTimes(), "通知成功");
                } else {
                    notifyQueue.addElementToList(notifyRecord);
                    notifyRecordService.updateNotifyRecord(notifyRecord.getId(), notifyRecord.getNotifyTimes(), "http请求响应成功");

                }
                LOG.info("Update NotifyRecord:" + JSONObject.toJSONString(notifyRecord) + ";responseMsg:" + responseMsg);
            } else {
                notifyQueue.addElementToList(notifyRecord);
                // 再次放到通知列表中，由添加程序判断是否已经通知完毕或者通知失败
                notifyRecordService.updateNotifyRecord(notifyRecord.getId(), notifyRecord.getNotifyTimes(), "http请求失败");
            }

            // 写通知日志表
            notifyRecordLogService.saveNotifyRecordLogs(notifyRecord.getId(), notifyRecord.getMerchantNo(), notifyRecord.getMerchantOrderNo(), notifyRecord.getUrl(), responseMsg, responseStatus);
            LOG.info("Insert NotifyRecordLog, merchantNo:" + notifyRecord.getMerchantNo() + ",merchantOrderNo:" + notifyRecord.getMerchantOrderNo());

        } catch (Exception e) {
            LOG.error("NotifyTask", e);
            notifyQueue.addElementToList(notifyRecord);

            notifyRecordService.updateNotifyRecord(notifyRecord.getId(), notifyRecord.getNotifyTimes(), "http请求失败");
            notifyRecordLogService.saveNotifyRecordLogs(notifyRecord.getId(), notifyRecord.getMerchantNo(), notifyRecord.getMerchantOrderNo(), notifyRecord.getUrl(), "", 0);
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }
}
