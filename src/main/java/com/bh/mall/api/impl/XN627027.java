package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627027Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理升级详情查询
 * @author nyc
 *
 */
public class XN627027 extends AProcessor{

	private IAgentUpgradeAO gentUpgradeAO = SpringContextHolder.getBean(IAgentUpgradeAO.class);
	private XN627027Req req = null;
	@Override
	public Object doBusiness() throws BizException {
		return gentUpgradeAO.getAgentUpgrade(req.getCode());
	}

	@Override
	public void doCheck(String inputparams) throws ParaException {
		req = JsonUtils.json2Bean(inputparams, XN627027Req.class);
		StringValidater.validateBlank(req.getCode());
	}

}
