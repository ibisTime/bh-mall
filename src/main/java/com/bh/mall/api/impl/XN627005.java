package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.dto.req.XN627005Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627005 extends AProcessor {

	private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);
	private XN627005Req req = null;
	@Override
	public Object doBusiness() throws BizException {
		Agent condition = new Agent();
		condition.setName(req.getName());
		condition.setLevel(req.getLevel());
		String cloumn = req.getOrderColumn();
		if(StringUtils.isBlank(cloumn)) {
			cloumn = IAgentAO.DEFAULT_ORDER_COLUMN;
		}
		condition.setOrder(cloumn, req.getOrderDir());
		int start = Integer.valueOf(req.getStart());
		int limit = Integer.valueOf(req.getLimit());
		return agentAO.queryAgentListPage(start, limit, condition);
	}

	@Override
	public void doCheck(String inputparams) throws ParaException {
		 req = JsonUtil.json2Bean(inputparams, XN627005Req.class);
	     StringValidater.validateNumber(req.getStart(), req.getLimit());
	}

}