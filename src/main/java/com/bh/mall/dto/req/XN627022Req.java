package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 代理升级更新
 * @author nyc
 *
 */
public class XN627022Req {

    // 编号 （必填）
    @NotBlank
    private String code;

    // 代理编号 （必填）
    @NotBlank
    private String level;

    // 本等级升级是否公司审核 （必填）
    @NotBlank
    private String isCompanyApprove;

    // 半门槛推荐人数 （必填）
    @NotBlank
    private String reNumber;

    // 本等级升级是否余额清零 （必填）
    @NotBlank
    private String isReset;

    // 更新人 （必填）
    @NotBlank
    private String updater;

    // 备注 （必填）
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

    public String getIsReset() {
        return isReset;
    }

    public void setIsReset(String isReset) {
        this.isReset = isReset;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getReNumber() {
        return reNumber;
    }

    public void setReNumber(String reNumber) {
        this.reNumber = reNumber;
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
