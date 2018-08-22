package com.bh.mall.dto.req;

/**
 * 列表出货订单
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:21:23 
 * @history:
 */
public class XN627936Req extends APageReq {

    private static final long serialVersionUID = 974166051092822055L;

    // （选填）关键字
    private String keyword;

    // （选填）状态
    private String status;

    // （选填）状态
    private String productName;

    public String getKeyword() {
        return keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
