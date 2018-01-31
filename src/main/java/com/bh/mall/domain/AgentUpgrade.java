package com.bh.mall.domain;

import java.math.BigInteger;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 
 * @author: nyc 
 * @since: 2018年1月31日 下午2:08:40 
 * @history:
 */
public class AgentUpgrade extends ABaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = 7360253837274350094L;

    // 编号
    private String code;

    // 代理编号
    private String agentCode;

    // 本等级升级是否公司审核
    private String isCompanyApprove;

    // 本等级升级首单总额
     private Long upgradeFirstAmount;

    // 半门槛推荐人数
    private Integer recommendNumber;

    // 本等级升级是否余额清零
    private String isReset;

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

    public String getIsCompanyApprove() {
        return isCompanyApprove;
    }

    public void setIsCompanyApprove(String isCompanyApprove) {
        this.isCompanyApprove = isCompanyApprove;
    }

    public Long getUpgradeFirstAmount() {
        return upgradeFirstAmount;
    }
  public void setUpgradeFirstAmount(Long upgradeFirstAmount) {
        this.upgradeFirstAmount = upgradeFirstAmount;
    }

   public Integer getRecommendNumber() {
        return recommendNumber;
    }

    public void setRecommendNumber(Integer recommendNumber) {
        this.recommendNumber = recommendNumber;
    }

    public String getIsReset() {
        return isReset;
    }

    public void setIsReset(String isReset) {
        this.isReset = isReset;
    }

}
