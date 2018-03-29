package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.dto.req.XN627025Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理升级分页查询
 * @author nyc
 *
 */
public class XN627025 extends AProcessor {

    private IAgentUpgradeAO agentUpgradeAO = SpringContextHolder
        .getBean(IAgentUpgradeAO.class);

    private XN627025Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgentUpgrade condition = new AgentUpgrade();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setIsCompanyApprove(req.getIsCompanyApprove());
        condition.setIsReset(req.getIsReset());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IAgentImpowerAO.DEFAULT_ORDER_COLUMN;
        }

        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return agentUpgradeAO.queryAgentUpgradeListPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627025Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        ObjValidater.validateReq(req);
    }

}
