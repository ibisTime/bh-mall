package com.bh.mall.dto.req;

/**
 * 详情查流水
 * @author: nyc 
 * @since: 2018年4月10日 下午8:35:28 
 * @history:
 */
public class XN627834Req extends APageReq {

    private static final long serialVersionUID = 1520054065067430175L;

    // （必填）产品 编号
    private String productCode;

    // （选填）状态
    private String status;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
