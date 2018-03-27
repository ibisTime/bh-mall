package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询奖励
 * @author: nyc 
 * @since: 2018年3月26日 上午10:57:40 
 * @history:
 */
public class XN627590Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 7191513045041458252L;

    // （必填）类型
    @NotBlank(message = "类型不能为空")
    private String type;

    // （选填）产品编号
    private String productCode;

    // （选填）等级
    private String level;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
