package com.bh.mall.domain;

import java.math.BigInteger;

import com.bh.mall.dao.base.ABaseDO;

public class AgentUpgrade extends ABaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7360253837274350094L;

	// 编号
	private String code;
	
	//代理编号
	private String agentCode;
	
	//本等级升级是否公司审核
	private String isCompanyApprove;
	
	// 本等级升级首单总额
	private BigInteger upgradeFirstAmount;
	
	// 半门槛推荐人数
	private int recommendNumber;
	
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

	public BigInteger getUpgradeFirstAmount() {
		return upgradeFirstAmount;
	}

	public void setUpgradeFirstAmount(BigInteger upgradeFirstAmount) {
		this.upgradeFirstAmount = upgradeFirstAmount;
	}

	public int getRecommendNumber() {
		return recommendNumber;
	}

	public void setRecommendNumber(int recommendNumber) {
		this.recommendNumber = recommendNumber;
	}

	public String getIsReset() {
		return isReset;
	}

	public void setIsReset(String isReset) {
		this.isReset = isReset;
	}

}
