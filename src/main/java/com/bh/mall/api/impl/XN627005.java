package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentLevelAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.dto.req.XN627005Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询代理等级
 * @author nyc
 *
 */
public class XN627005 extends AProcessor {

    private IAgentLevelAO agentAO = SpringContextHolder
        .getBean(IAgentLevelAO.class);

    private XN627005Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgentLevel condition = new AgentLevel();
        condition.setName(req.getName());
        condition.setLowLevel(StringValidater.toInteger(req.getLowLevel()));
        condition.setHighLevel(StringValidater.toInteger(req.getHighLevel()));

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IAgentLevelAO.DEFAULT_ORDER_COLUMN;
        }

        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return agentAO.queryAgentLevelListPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627005Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
