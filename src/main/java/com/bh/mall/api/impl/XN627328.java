package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.ao.IAgentLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.dto.req.XN627328Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询代理轨迹
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627328 extends AProcessor {

    private IAgentLogAO agentLogAO = SpringContextHolder
        .getBean(IAgentLogAO.class);

    private XN627328Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgentLog condition = new AgentLog();
        condition.setApplyUser(req.getUserId());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAgentAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return agentLogAO.queryAgentLogList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627328Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getStart(),
            req.getLimit());
    }

}
