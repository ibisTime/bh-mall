package com.bh.mall.dto.req;

import java.util.List;

/**
 * 分页
 * @author: nyc 
 * @since: 2018年3月28日 下午8:54:38 
 * @history:
 */

public class XN627665Req extends APageReq {

    private static final long serialVersionUID = 1153648985518580126L;

    // 订单状态
    private String status;

    // 订单状态List
    private List<String> statusList;

    // 类型
    private List<String> kindList;

    // 类型
    private String keyword;

    // 下单人
    private String applyUser;

    // 产品名称
    private String productName;

    // 下单代理等级
    private String level;

    // 发货人
    private String deliver;

    // 订单归属人
    private String toUserId;

    // 是否云仓发货
    private String isWareSend;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<String> getKindList() {
        return kindList;
    }

    public void setKindList(List<String> kindList) {
        this.kindList = kindList;
    }

    public String getProductName() {
        return productName;
    }

    public String getLevel() {
        return level;
    }

    public String getDeliver() {
        return deliver;
    }

    public String getIsWareSend() {
        return isWareSend;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public void setIsWareSend(String isWareSend) {
        this.isWareSend = isWareSend;
    }

}
