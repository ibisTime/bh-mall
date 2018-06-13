package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改代理
 * @author nyc
 *
 */
public class XN627002Req {

    // 等级（必填）
    @NotBlank(message = "等级不能为空")
    private String level;

    // 等级名称（必填）
    @NotBlank(message = "等级名称不能为空")
    private String name;

    // （必填） 首次授权发货金额
    @NotBlank(message = "首次授权发货金额不能为空")
    private String amount;

    // (必填) 本等级最低充值金额
    @NotBlank(message = "本等级最低充值金额不能为空")
    private String minChargeAmount;

    // (必填) 红线金额
    @NotBlank(message = "红线金额不能为空")
    private String redAmount;

    // （必填） 更新人
    @NotBlank(message = " 更新人不能为空")
    private String updater;

    // （选填） 备注
    private String remark;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMinChargeAmount() {
        return minChargeAmount;
    }

    public void setMinChargeAmount(String minChargeAmount) {
        this.minChargeAmount = minChargeAmount;
    }

    public String getRedAmount() {
        return redAmount;
    }

    public void setRedAmount(String redAmount) {
        this.redAmount = redAmount;
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
