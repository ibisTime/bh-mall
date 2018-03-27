package com.bh.mall.dto.req;

/**
 * 列表查询库存记录
 * @author: nyc 
 * @since: 2018年3月26日 下午1:58:20 
 * @history:
 */
public class XN627611Req {

    // 创建起始时间
    private String dateStart;

    // 创建终止时间
    private String dateEnd;

    // （选填）产品编号
    private String productCode;

    // （选填） 类型 0出库 1入库
    private String type;

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

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
