package com.xh.pay.facility.model;

import java.util.Date;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年9月26日
 */
public class NotifyRecord {

	private Long id;// 主键ID.
	private Integer version = 0;// 版本号默认为0
	private String status;// 状态 PublicStatusEnum
	private String creater;// 创建人.
	private Date createTime;// 创建时间.
	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 描述

	/** 最后一次通知时间 **/
	private Date lastNotifyTime;
	/** 通知次数 **/
	private Integer notifyTimes = 0;
	/** 限制通知次数 **/
	private Integer limitNotifyTimes = 5;
	/** 通知URL **/
	private String url;
	/** 商户编号 **/
	private String merchantNo;
	/** 商户订单号 **/
	private String merchantOrderNo;
	/** 通知类型 NotifyTypeEnum **/
	private String notifyType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLastNotifyTime() {
		return lastNotifyTime;
	}

	public void setLastNotifyTime(Date lastNotifyTime) {
		this.lastNotifyTime = lastNotifyTime;
	}

	public Integer getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(Integer notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public Integer getLimitNotifyTimes() {
		return limitNotifyTimes;
	}

	public void setLimitNotifyTimes(Integer limitNotifyTimes) {
		this.limitNotifyTimes = limitNotifyTimes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

}
