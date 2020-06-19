package com.xh.pay.facility.service;


import com.xh.pay.facility.model.OrderResultQuery;

/**
 * Title: 持续轮询订单结果
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
public interface PollingPersistService {

    /**
     * 获取订单结果
     * @param orderResultQuery
     */
    void getOrderResult(OrderResultQuery orderResultQuery);
}
