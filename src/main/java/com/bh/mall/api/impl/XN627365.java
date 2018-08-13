package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.dto.req.XN627365Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询代理轨迹
 * @author: nyc 
 * @since: 2018年8月6日 下午8:50:31 
 * @history:
 */
public class XN627365 extends AProcessor {

    private IAgentLogAO agentLogAO = SpringContextHolder
        .getBean(IAgentLogAO.class);

    private XN627365Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgentLog condition = new AgentLog();
        condition.setApplyUser(req.getUserId());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setStatus(req.getStatus());
        condition.setKeyword(req.getKeyword());

        condition.setRealName(req.getRealName());
        condition.setMobile(req.getMobile());
        condition.setWxId(req.getWxId());
        condition.setTeamName(req.getTeamName());

        condition.setApproveDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setApproveDatetimeEnd(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAgentLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return agentLogAO.queryAgentLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627365Req.class);
        ObjValidater.validateReq(req);
    }

}
