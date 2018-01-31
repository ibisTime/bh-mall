package com.bh.mall.dto.req;

/**
 * 代理升级更新
 * @author nyc
 *
 */
public class XN627022Req {

	// 代理编号 （必填）
	private String agentCode;
	// 编号 （必填）
	private String code;
	// 本等级升级是否公司审核 （必填）
	private String isCompanyApprove;
	// 本等级升级是否余额清零 （必填）
	private String isReset;
	// 半门槛推荐人数 （必填）
	private String recommendNumber;
	// 本等级升级首单总额（必填）
	private String upgradeFirstAmount;
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
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
	public String getRecommendNumber() {
		return recommendNumber;
	}
	public void setRecommendNumber(String recommendNumber) {
		this.recommendNumber = recommendNumber;
	}
	public String getUpgradeFirstAmount() {
		return upgradeFirstAmount;
	}
	public void setUpgradeFirstAmount(String upgradeFirstAmount) {
		this.upgradeFirstAmount = upgradeFirstAmount;
	}
	
}
