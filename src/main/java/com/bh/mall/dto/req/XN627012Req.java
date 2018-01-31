package com.bh.mall.dto.req;

/**
 * 修改代理授权
 * @author nyc
 *
 */
public class XN627012Req {

    // 编号 （必填）
    private String code;

    // 代理编号 （必填）
    private String agentCode;

    // 授权金额 （必填）
    private String impowerAmount;

    // 本等级是否可以被意向 （必填）
    private String isIntent;

    // 是否可以被介绍 （必填）
    private String isIntro;

    // 是否需要公司审核 （必填）
    private String isCompanyImpower;

    // 是否需要实名 （必填）
    private String isRealName;

    // 本等级授权单是否汇总 （必填）
    private String isSummary;

    // 本等级充值门槛 （必填）
    private String minCharge;

    // 红线设置 （必填）
    private String redPercent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getImpowerAmount() {
        return impowerAmount;
    }

    public void setImpowerAmount(String impowerAmount) {
        this.impowerAmount = impowerAmount;
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

    public String getIsSummary() {
        return isSummary;
    }

    public void setIsSummary(String isSummary) {
        this.isSummary = isSummary;
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge;
    }

    public String getRedPercent() {
        return redPercent;
    }

    public void setRedPercent(String redPercent) {
        this.redPercent = redPercent;
    }

}
