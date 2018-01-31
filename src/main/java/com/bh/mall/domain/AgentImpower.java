package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 等级授权设置
 * @author: chenshan 
 * @since: 2018年1月31日 下午1:51:00 
 * @history:
 */
public class AgentImpower extends ABaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = -6038753576544375788L;

    // 编号
    private String code;

    // 代理编号
    private String agentCode;

    // 本等级是够可被意向
    private String isIntent;

    // 本等级是否可被介绍
    private String isIntro;

    // 本等级是否需要实名
    private String isRealname;

    // 本等级是否需要公司审核(授权)
    private String isCompanyImpower;

    // 本等级授权单金额
    private Long impowerAmount;

    // 本等级充值门槛
    private Long minCharge;

    // 红线金额百分比
    private Double redPercent;

    // 本等级授权是否汇总
    private String isSummary;

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

    public String getIsRealname() {
        return isRealname;
    }

    public void setIsRealname(String isRealname) {
        this.isRealname = isRealname;
    }

    public String getIsCompanyImpower() {
        return isCompanyImpower;
    }

    public void setIsCompanyImpower(String isCompanyImpower) {
        this.isCompanyImpower = isCompanyImpower;
    }

    public Long getImpowerAmount() {
        return impowerAmount;
    }

    public void setImpowerAmount(Long impowerAmount) {
        this.impowerAmount = impowerAmount;
    }

    public Long getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(Long minCharge) {
        this.minCharge = minCharge;
    }

    public Double getRedPercent() {
        return redPercent;
    }

    public void setRedPercent(Double redPercent) {
        this.redPercent = redPercent;
    }

    public String getIsSummary() {
        return isSummary;
    }

    public void setIsSummary(String isSummary) {
        this.isSummary = isSummary;
    }

}
