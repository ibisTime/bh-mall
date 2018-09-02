package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 运费
 * @author: nyc 
 * @since: 2018年3月28日 下午8:54:38 
 * @history:
 */

public class XN627666Req {

    // 规格编号
    @NotBlank(message = "产品编号不能为空")
    private String productCode;

    // 规格编号
    @NotBlank(message = "规格编号不能为空")
    private String specsCode;

    // 数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // 省
    @NotBlank(message = "省不能为空")
    private String province;

    // 类型（0 普通产品 1内购产品）
    @NotBlank(message = "种类不能为空")
    private String kind;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProvince() {
        return province;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
