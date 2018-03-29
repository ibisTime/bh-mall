package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 申请售后
 * @author: nyc 
 * @since: 2018年3月28日 下午9:02:41 
 * @history:
 */

public class XN627680Req {

    // （必填） 回寄地址编号
    @NotBlank(message = " 回寄地址编号不能为空")
    private String addressCode;

    // （必填）申请理由
    @NotBlank(message = "申请理由不能为空")
    private String applyNote;

    // （必填）申请人
    @NotBlank(message = "申请人不能为空")
    private String applyUser;

    // （必填）关联订单
    @NotBlank(message = "关联订单不能为空")
    private String refNo;

    // （必填）图片
    @NotBlank(message = "图片不能为空")
    private String pic;

    // （必填）换货类型
    @NotBlank(message = "换货类型不能为空")
    private String type;

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
