package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 审核取消
 * @author: nyc 
 * @since: 2018年8月7日 下午9:40:02 
 * @history:
 */
public class XN627904Req {

    // （必填） 订单编号
    @NotBlank(message = "订单编号不能为空")
    private String code;

    // （必填） 审核人
    @NotBlank(message = "审核人不能为空")
    private String approver;

    // （必填） 结果
    @NotBlank(message = " 结果不能为空")
    private String result;

    // （必填） 备注
    @NotBlank(message = "备注不能为空")
    private String remark;

    public String getCode() {
        return code;
    }

    public String getApprover() {
        return approver;
    }

    public String getRemark() {
        return remark;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
