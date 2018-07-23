package com.bh.mall.dto.req;

import java.util.List;

/**
 * 分页
 * @author: nyc 
 * @since: 2018年3月28日 下午8:54:38 
 * @history:
 */

public class XN627662Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1153648985518580126L;

    // 类型
    private String kind;

    // 订单状态
    private String status;

    // 查询待处理订单
    private List<String> statusList;

    // 下单人
    private String keyword;

    // 产品名称
    private String productName;

    // 下单代理等级
    private String level;

    // 发货人
    private String deliver;

    // 订单归属人
    private String toUser;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

}
