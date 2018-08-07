package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 内购产品规格-新增
 * @author: LENOVO 
 * @since: 2018年8月1日 下午4:01:42 
 * @history:
 */
public class XN627900Req {

    // （必填）下单人
    @NotBlank(message = "下单人不能为空")
    private String applyUser;

    // （必填） 订单归属人
    @NotBlank(message = "订单归属人不能为空")
    private String toUserId;

    // （必填）产品规格编号
    @NotBlank(message = "产品规格编号不能为空")
    private String specsCode;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （选填）下单备注
    private String applyNote;

    public String getApplyUser() {
        return applyUser;
    }

    public String getToUserId() {
        return toUserId;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

}
