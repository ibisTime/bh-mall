package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627016Req;
import com.bh.mall.dto.req.XN627026Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

public class XN627026 extends AProcessor {

	private IAgentUpgradeAO agentUpgradeAO = SpringContextHolder.getBean(IAgentUpgradeAO.class);
	private XN627026Req req;
	@Override
	public Object doBusiness() throws BizException {
		return agentUpgradeAO.getAgentUpgrade(req.getAgentCode());
	}

	@Override
	public void doCheck(String inputparams) throws ParaException {
		req = JsonUtils.json2Bean(inputparams, XN627026Req.class);
		StringValidater.validateBlank(req.getAgentCode());
	}

}
