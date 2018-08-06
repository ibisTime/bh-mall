package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.dto.req.XN627366Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询代理轨迹
 * @author: nyc 
 * @since: 2018年8月6日 下午8:50:31 
 * @history:
 */
public class XN627366 extends AProcessor {

    private IAgentLogAO agentLogAO = SpringContextHolder
        .getBean(IAgentLogAO.class);

    private XN627366Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgentLog condition = new AgentLog();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setStatus(req.getStatus());
        condition.setKeyword(req.getKeyword());

        condition.setApproveDatetimeStrat(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setApproveDatetimeEnd(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAgentLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return agentLogAO.queryAgentLogList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627366Req.class);
    }

}
