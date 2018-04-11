package com.bh.mall.dto.req;

/**
 * 列表查订单
 * @author: nyc 
 * @since: 2018年3月28日 下午8:54:38 
 * @history:
 */

public class XN627663Req {

    // 类型
    private String kind;

    // 订单状态
    private String status;

    // 所属团队
    private String keyword;

    // 结束时间
    private String dateEnd;

    // 开始时间
    private String dateStart;

    // 产品名称
    private String productName;

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

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

}
