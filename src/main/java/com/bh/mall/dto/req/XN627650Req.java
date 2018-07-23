package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单作废
 * @author: nyc 
 * @since: 2018年3月28日 下午8:46:36 
 * @history:
 */

public class XN627650Req {

    // （必填） 订单编号
    @NotBlank(message = "订单编号不能为空")
    private String code;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （必填）备注
    @NotBlank(message = "备注不能为空")
    private String remark;

    public String getCode() {
        return code;
    }

    public String getUpdater() {
        return updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
