package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 申请换货
 * @author: nyc 
 * @since: 2018年4月9日 下午4:01:24 
 * @history:
 */
public class XN627790Req {

    // （必填）申请人
    @NotBlank(message = "申请人不能为空")
    private String applyUser;

    // （必填）商品规格编号
    @NotBlank(message = "商品规格编号不能为空")
    private String productSpecsCode;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （必填）置换商品规格编号
    @NotBlank(message = "置换商品规格编号不能为空")
    private String changeSpecsCode;

    // (选填) 申请备注
    private String applyNote;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public void setProductSpecsCodeList(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getChangeSpecsCode() {
        return changeSpecsCode;
    }

    public void setChangeSpecsCode(String changeSpecsCode) {
        this.changeSpecsCode = changeSpecsCode;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
    }

    public String getApplyNote() {
        return applyNote;
    }

}
