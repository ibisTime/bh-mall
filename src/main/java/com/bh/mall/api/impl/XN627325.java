package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.dto.req.XN627325Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询用户
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627325 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627325Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Agent condition = new Agent();
        condition.setMobile(req.getMobile());
        condition.setHighLevel(StringValidater.toInteger(req.getHighLevel()));
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setReferrer(req.getUserReferee());

        condition.setRealName(req.getRealName());
        condition.setStatus(req.getStatus());
        condition.setWxId(req.getWxId());
        condition.setTeamName(req.getTeamName());

        condition.setNoStatusList(req.getNoStatusList());
        condition.setUnionId(req.getUnionId());
        condition.setH5OpenId(req.getH5OpenId());
        condition.setAppOpenId(req.getAppOpenId());
        condition.setStatusList(req.getStatusList());

        condition.setCreateDatetimeStart(DateUtil.strToDate(
            req.getApplyStartDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateDatetimeEnd(DateUtil.strToDate(
            req.getApplyEndDatetime(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAgentAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return agentAO.queryAgentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627325Req.class);
        ObjValidater.validateReq(req);
    }

}
