package com.bh.mall.dto.req;

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
    private String type;

    // 订单状态
    private String status;

    // 查询待处理订单
    private String statusList;

    // 下单人
    private String keyword;

    // 产品名称
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatusList() {
        return statusList;
    }

    public void setStatusList(String statusList) {
        this.statusList = statusList;
    }

}
