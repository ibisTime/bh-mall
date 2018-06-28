package com.bh.mall.dto.req;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除出货奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:34:58 
 * @history:
 */
public class XN627862Req {

    // （必填） 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填） 奖励百分比
    @NotBlank(message = "奖励百分比不能为空")
    private String percent;

    // （必填） 区间起始金额
    @NotBlank(message = "区间起始金额不能为空")
    @Min(0)
    private String startAmount;

    // （必填） 区间截止金额
    @NotBlank(message = " 区间截止金额不能为空")
    @Min(0)
    private String endAmount;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填 ）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPercent() {
        return percent;
    }

    public String getStartAmount() {
        return startAmount;
    }

    public String getEndAmount() {
        return endAmount;
    }

    public String getUpdater() {
        return updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount;
    }

    public void setEndAmount(String endAmount) {
        this.endAmount = endAmount;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
