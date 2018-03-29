package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发货
 * @author: nyc 
 * @since: 2018年3月28日 下午1:59:11 
 * @history:
 */
public class XN627647Req {
    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填） 结果(0，通过；1，不通过)
    @NotBlank(message = " 结果不能为空")
    private String result;

    // （必填）审核人
    @NotBlank(message = "审核人不能为空")
    private String updater;

    // （选填）审核备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
