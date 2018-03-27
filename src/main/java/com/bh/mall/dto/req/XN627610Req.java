package com.bh.mall.dto.req;

/**
 * 分页查询库存记录
 * @author: nyc 
 * @since: 2018年3月26日 下午1:58:03 
 * @history:
 */
public class XN627610Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -4982309191151229749L;

    // （选填）产品编号
    private String productCode;

    // （选填） 类型 0出库 1入库
    private String type;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
