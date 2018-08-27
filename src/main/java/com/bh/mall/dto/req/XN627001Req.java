package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改代理
 * @author nyc
 *
 */
public class XN627001Req {

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
    @NotBlank(message = "是否需要实名 不能为空")
    private String isRealName;

    // 是否需要公司审核 （必填）
    @NotBlank(message = "是否需要公司审核不能为空")
    private String isCompanyImpower;

    // （必填）门槛款
    @NotBlank(message = "门槛款不能为空")
    private String minCharge;

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
