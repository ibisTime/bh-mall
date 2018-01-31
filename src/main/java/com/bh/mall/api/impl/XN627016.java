package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.dto.req.XN627016Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理授权列表查询
 * @author: chenshan 
 * @since: 2018年1月31日 上午9:53:37 
 * @history:
 */
public class XN627016 extends AProcessor {

    private IAgentImpowerAO agentImpowerAO = SpringContextHolder
        .getBean(IAgentImpowerAO.class);

    private XN627016Req req;

    @Override
    public Object doBusiness() throws BizException {
    	AgentImpower condition = new AgentImpower();
    	condition.setAgentCode(req.getAgentCode());
        return agentImpowerAO.queryAgentImpowerList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627016Req.class);
    }

}
