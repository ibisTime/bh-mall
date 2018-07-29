package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.dto.req.XN627266Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询用户
 * @author: chenshan 
 * @since: 2018年3月26日 下午6:28:36 
 * @history:
 */
public class XN627266 extends AProcessor {
    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627266Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Agent condition = new Agent();
        condition.setLoginNameForQuery(req.getLoginName());
        condition.setRoleCode(req.getRoleCode());
        condition.setMobile(req.getMobile());
        condition.setHighLevel(StringValidater.toInteger(req.getHighLevel()));
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setUserReferee(req.getUserReferee());

        condition.setRealName(req.getRealName());
        condition.setStatus(req.getStatus());

        condition.setUnionId(req.getUnionId());
        condition.setH5OpenId(req.getH5OpenId());
        condition.setAppOpenId(req.getAppOpenId());

        condition.setApplyDatetimeStart(
            DateUtil.getFrontDate(req.getApplyStartDatetime(), false));
        condition.setApplyDatetimeEnd(
            DateUtil.getFrontDate(req.getApplyEndDatetime(), true));

        condition.setCreateDatetimeStart(
            DateUtil.getFrontDate(req.getCreateDatetimeStart(), false));
        condition.setCreateDatetimeEnd(
            DateUtil.getFrontDate(req.getCreateDatetimeEnd(), true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return agentAO.queryAgentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627266Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
