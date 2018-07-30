package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改代理
 * @author nyc
 *
 */
public class XN627002Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 等级（必填）
    @NotBlank(message = "等级不能为空")
    private String level;

    // 等级名称（必填）
    @NotBlank(message = "等级名称不能为空")
    private String name;

    // 本等级是否可以被意向 （必填）
    @NotBlank(message = "本等级是否可以被意向不能为空")
    private String isIntent;

    // 是否可以被介绍 （必填）
    @NotBlank(message = "是否可以被介绍不能为空")
    private String isJsAward;

    // 是否需要实名 （必填）
    @NotBlank(message = "是否需要实名不能为空")
    private String isRealName;

    // （必填）授权单金额
    @NotBlank(message = "授权单金额不能为空")
    private String amount;

    // （必填）本等级授权单允许自发
    @NotBlank(message = "本等级授权单是否自发")
    private String isSend;

    // 是否需要公司审核 （必填）
    @NotBlank(message = "授权是否需要公司审核 不能为空")
    private String isCompanyImpower;

    // 本等级是否启用云仓
    @NotBlank(message = "是否启用云仓不能为空")
    private String isWare;

    // (必填) 本等级最低充值金额
    @NotBlank(message = "本等级最低充值金额不能为空")
    private String minChargeAmount;

    // （必填）本等级门槛最低余额
    @NotBlank(message = "本等级门槛最低余额不能为空")
    private String minSurplus;

    // (必填) 红线金额
    @NotBlank(message = "红线金额不能为空")
    private String redAmount;

    // 每次充值门槛最低金额（必填）
    @NotBlank(message = "每次充值门槛最低金额不能为空")
    private String minCharge;

    // 本等级升级是否公司审核 （必填）
    @NotBlank(message = "本等级升级是否公司审核不能为空")
    private String isCompanyApprove;

    // 半门槛推荐人数 （必填）
    @NotBlank(message = "半门槛推荐人数 不能为空")
    private String reNumber;

    // 升级门槛是否清零
    @NotBlank(message = "升级门槛是否清零不能为空")
    private String isReset;

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

    public String getIsCompanyApprove() {
        return isCompanyApprove;
    }

    public void setIsCompanyApprove(String isCompanyApprove) {
        this.isCompanyApprove = isCompanyApprove;
    }

    public String getReNumber() {
        return reNumber;
    }

    public void setReNumber(String reNumber) {
        this.reNumber = reNumber;
    }

    public String getIsReset() {
        return isReset;
    }

    public void setIsReset(String isReset) {
        this.isReset = isReset;
    }

    public String getIsIntent() {
        return isIntent;
    }

    public void setIsIntent(String isIntent) {
        this.isIntent = isIntent;
    }

    public String getIsJsAward() {
        return isJsAward;
    }

    public void setIsJsAward(String isJsAward) {
        this.isJsAward = isJsAward;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }

    public String getIsCompanyImpower() {
        return isCompanyImpower;
    }

    public void setIsCompanyImpower(String isCompanyImpower) {
        this.isCompanyImpower = isCompanyImpower;
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge;
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

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getImpowerAmount() {
        return amount;
    }

    public void setImpowerAmount(String impowerAmount) {
        this.amount = impowerAmount;
    }

}
