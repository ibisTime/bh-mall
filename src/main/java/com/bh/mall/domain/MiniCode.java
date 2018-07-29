package com.bh.mall.domain;

import java.util.Date;
import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
* 防伪溯源码
* @author: nyc 
* @since: 2018-07-01 21:43:49
* @history:
*/
public class MiniCode extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // **************db*************

    // 防伪码
    private String miniCode;

    // 溯源码
    private String traceCode;

    // 关联条形码
    private String refCode;

    // 关联订单编号
    private String orderCode;

    // 状态（未使用，已使用）
    private String status;

    // 查询次数
    private Integer number;

    // 生成批次
    private String batch;

    // 生成时间
    private Date createDatetime;

    // 使用时间
    private Date useDatetime;

    // **************db*************

    // 订单
    private Order orderData;

    // 状态List
    private List<String> statusList;

    // 关键字
    private String keyword;

    public void setminiCode(String miniCode) {
        this.miniCode = miniCode;
    }

    public String getminiCode() {
        return miniCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

    public String getTraceCode() {
        return traceCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public Date getUseDatetime() {
        return useDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void setUseDatetime(Date useDatetime) {
        this.useDatetime = useDatetime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Order getOrderData() {
        return orderData;
    }

    public void setOrderData(Order orderData) {
        this.orderData = orderData;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
