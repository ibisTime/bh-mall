package com.bh.mall.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.bh.mall.dao.base.ABaseDO;

public class AgentImpower extends ABaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6038753576544375788L;

	// 编号
	private String code;
	
	//代理编号
	private String agentCode;
	
	//本等级是够可被意向
	private String isIntent;
	
	// 本等级是否可被介绍
	private String isIntro;
	
	// 本等级是否需要实名
	private String isRealname;
	
	// 本等级是否需要公司审核(授权)
	private String isCompanyImpower;
	
	// 本等级授权单金额
	private BigInteger impowerAmount;
	
	// 本等级充值门槛
	private BigInteger minCharge;
	
	// 红线金额百分比  
	private BigDecimal redPercent;
	
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

	public BigInteger getImpowerAmount() {
		return impowerAmount;
	}

	public void setImpowerAmount(BigInteger impowerAmount) {
		this.impowerAmount = impowerAmount;
	}

	public BigInteger getMinCharge() {
		return minCharge;
	}

	public void setMinCharge(BigInteger minCharge) {
		this.minCharge = minCharge;
	}

	public BigDecimal getRedPercent() {
		return redPercent;
	}

	public void setRedPercent(BigDecimal redPercent) {
		this.redPercent = redPercent;
	}

	public String getIsSummary() {
		return isSummary;
	}

	public void setIsSummary(String isSummary) {
		this.isSummary = isSummary;
	}

}
