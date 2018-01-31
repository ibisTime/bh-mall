package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627012Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改代理授权
 * @author nyc
 * @since: 2018年1月31日 下午1:12:04 
 * @history:
 */
public class XN627012 extends AProcessor {

    private IAgentImpowerAO agentImpowerAO = SpringContextHolder
        .getBean(IAgentImpowerAO.class);

    private XN627012Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return agentImpowerAO.editAgentImpower(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627012Req.class);
        StringValidater.validateBlank(req.getAgentCode(),
            req.getIsCompanyImpower(), req.getIsIntent(), req.getIsIntro(),
            req.getIsRealName(), req.getIsSummary(), req.getImpowerAmount(),
            req.getMinCharge(), req.getRedPercent());
        StringValidater.validateNumber(req.getImpowerAmount(),
            req.getMinCharge(), req.getRedPercent());
    }

}
