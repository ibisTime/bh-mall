package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.dto.req.XN627006Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

public class XN627006 extends AProcessor{

	private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);
	private XN627006Req req = null;
	@Override
	public Object doBusiness() throws BizException {
		return agentAO.queryAgentList(req.getLevel(), req.getName());
	}

	@Override
	public void doCheck(String inputparams) throws ParaException {
		req = JsonUtils.json2Bean(inputparams, XN627006Req.class);
	}

}
