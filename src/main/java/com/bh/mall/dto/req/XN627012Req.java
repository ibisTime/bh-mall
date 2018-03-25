package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改代理授权
 * @author nyc
 *
 */
public class XN627012Req {

    // 编号 （必填）
    @NotBlank
    private String code;

    // 代理编号 （必填）
    @NotBlank
    private String level;

    // 本等级是否可以被意向 （必填）
    @NotBlank
    private String isIntent;

    // 是否可以被介绍 （必填）
    @NotBlank
    private String isIntro;

    // 是否需要公司审核 （必填）
    @NotBlank
    private String isCompanyImpower;

    // 是否需要实名 （必填）
    @NotBlank
    private String isRealName;

    // 本等级充值门槛 （必填）
    @NotBlank
    private String minCharge;

    // 更新人 （必填）
    @NotBlank
    private String updater;

    // 备注
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

    public String getIsIntro() {
        return isIntro;
    }

    public void setIsIntro(String isIntro) {
        this.isIntro = isIntro;
    }

    public String getIsCompanyImpower() {
        return isCompanyImpower;
    }

    public void setIsCompanyImpower(String isCompanyImpower) {
        this.isCompanyImpower = isCompanyImpower;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
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
