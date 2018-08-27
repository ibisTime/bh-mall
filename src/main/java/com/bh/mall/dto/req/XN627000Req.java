package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改代理
 * @author nyc
 *
 */
public class XN627000Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 等级（必填）
    @NotBlank(message = "等级不能为空")
    private String level;

    // 等级名称（必填）
    @NotBlank(message = "等级名称不能为空")
    private String name;

    // （必填）授权单金额
    @NotBlank(message = "授权单金额不能为空")
    private String amount;

    // 本等级是否启用云仓
    @NotBlank(message = "是否启用云仓不能为空")
    private String isWare;

    // (必填) 本等级最低充值金额
    @NotBlank(message = "本等级最低充值金额不能为空")
    private String minChargeAmount;

    // （必填）门槛可有余额
    @NotBlank(message = "门槛可有余额不能为空")
    private String minSurplus;

    // (必填) 红线金额
    @NotBlank(message = "红线金额不能为空")
    private String redAmount;

    // （必填） 更新人
    @NotBlank(message = " 更新人不能为空")
    private String updater;

    // （选填） 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getMinSurplus() {
        return minSurplus;
    }

    public void setMinSurplus(String minSurplus) {
        this.minSurplus = minSurplus;
    }

    public String getIsWare() {
        return isWare;
    }

    public void setIsWare(String isWare) {
        this.isWare = isWare;
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

    public String getImpowerAmount() {
        return amount;
    }

    public void setImpowerAmount(String impowerAmount) {
        this.amount = impowerAmount;
    }

}
