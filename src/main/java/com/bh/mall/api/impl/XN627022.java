package com.bh.mall.api.impl;


import java.math.BigInteger;

import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.dto.req.XN627022Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理升级更新
 * @author nyc
 *
 */
public class XN627022 extends AProcessor {

	private IAgentUpgradeAO agentUpgradeAO = SpringContextHolder.getBean(IAgentUpgradeAO.class);
	private XN627022Req req = null;
	
	@Override
	public Object doBusiness() throws BizException {
		AgentUpgrade data = new AgentUpgrade();
		data.setAgentCode(req.getAgentCode());
		data.setCode(req.getCode());
		data.setIsCompanyApprove(req.getIsCompanyApprove());
		data.setIsReset(req.getIsReset());
		data.setRecommendNumber(Integer.valueOf(req.getRecommendNumber()));
		data.setUpgradeFirstAmount(BigInteger.valueOf(Long.valueOf(req.getUpgradeFirstAmount())));
		return agentUpgradeAO.updateAgentUpgrade(data);
	}

	@Override
	public void doCheck(String inputparams) throws ParaException {
		req = JsonUtil.json2Bean(inputparams, XN627022Req.class);
		StringValidater.validateBlank(req.getAgentCode(), req.getCode(),req.getIsCompanyApprove(), req.getIsReset(),
				req.getRecommendNumber(), req.getUpgradeFirstAmount());
		StringValidater.validateNumber(req.getRecommendNumber(), req.getUpgradeFirstAmount());
	}

}
