package com.bh.mall.api.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.dto.req.XN627012Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改代理授权
 * @author nyc
 *
 */
public class XN627012 extends AProcessor {

	private IAgentImpowerAO agentImpowerAO = SpringContextHolder.getBean(IAgentImpowerAO.class);
	private XN627012Req req;
	@Override
	public Object doBusiness() throws BizException {
		AgentImpower data = new AgentImpower();
		data.setCode(req.getCode());
		data.setAgentCode(req.getAgentCode());
		data.setImpowerAmount(BigInteger.valueOf(Long.valueOf(req.getImpowerAmount())));
		data.setIsCompanyImpower(req.getIsCompanyImpower());
		data.setIsIntent(req.getIsIntent());
		data.setIsIntro(req.getIsIntro());
		data.setIsRealname(req.getIsRealName());
		data.setIsSummary(req.getIsSummary());
		data.setMinCharge(BigInteger.valueOf(Long.valueOf(req.getMinCharge())));
		data.setRedPercent(BigDecimal.valueOf(Double.valueOf(req.getRedPercent())));
		return agentImpowerAO.editAgentImpower(data);
	}

	@Override
	public void doCheck(String inputparams) throws ParaException {
		req = JsonUtils.json2Bean(inputparams, XN627012Req.class);
		StringValidater.validateBlank(req.getAgentCode(),  req.getIsCompanyImpower(), req.getIsIntent(),
				req.getIsIntro(), req.getIsRealName(),req.getIsSummary(),req.getImpowerAmount(), 
				req.getMinCharge(), req.getRedPercent());
		StringValidater.validateNumber(req.getImpowerAmount(), req.getMinCharge(), req.getRedPercent());
	}

}
