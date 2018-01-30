package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627016Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

public class XN627016 extends AProcessor {

	private IAgentImpowerAO agentImpowerAO = SpringContextHolder.getBean(IAgentImpowerAO.class);
	private XN627016Req req;
	@Override
	public Object doBusiness() throws BizException {
		return agentImpowerAO.queryAgentImpowerList(req.getCode(), req.getAgentCode());
	}

	@Override
	public void doCheck(String inputparams) throws ParaException {
		req = JsonUtils.json2Bean(inputparams, XN627016Req.class);
		StringValidater.validateBlank(req.getCode());
	}

}
