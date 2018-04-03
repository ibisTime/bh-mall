package com.bh.mall.dto.req;

/**
 * 分页
 * @author: nyc 
 * @since: 2018年3月28日 下午8:54:38 
 * @history:
 */

public class XN627665Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1153648985518580126L;

    // 订单状态
    private String status;

    // 产品名称
    private String productName;

    // 类型
    private String kind;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
