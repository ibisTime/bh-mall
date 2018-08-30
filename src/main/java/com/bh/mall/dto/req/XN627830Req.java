package com.bh.mall.dto.req;

/**
 * 分页查流水
 * @author: nyc 
 * @since: 2018年4月10日 下午8:35:28 
 * @history:
 */
public class XN627830Req extends APageReq {

    private static final long serialVersionUID = 132773723046353379L;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 产品名称
    private String specsCode;

    // （选填）申请人
    private String keyword;

    // （选填）等级
    private String level;

    // （选填）状态 0 申请置换 1 申请通过 2 申请未通过
    private String status;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

}
