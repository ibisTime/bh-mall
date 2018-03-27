package com.bh.mall.dto.req;

/**
 * 申请取消内购订单
 * @author: nyc 
 * @since: 2018年3月27日 下午5:46:17 
 * @history:
 */
public class XN627724Req {

    // （必填）编号
    private String code;

    // （必填）审核人
    private String applyUser;

    // （选填）备注
    private String applyNote;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

}
