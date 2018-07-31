package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentLevelAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.dto.req.XN627006Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询列表代理
 * @author: nyc 
 * @since: 2018年1月31日 下午2:52:34 
 * @history:
 */
public class XN627006 extends AProcessor {

    private IAgentLevelAO agentAO = SpringContextHolder.getBean(IAgentLevelAO.class);

    private XN627006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgentLevel condition = new AgentLevel();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setName(req.getName());
        return agentAO.queryAgentLevelList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627006Req.class);
    }

}
