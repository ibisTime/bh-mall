package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627022Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理升级更新
 * @author nyc
 *
 */
public class XN627022 extends AProcessor {

    private IAgentUpgradeAO agentUpgradeAO = SpringContextHolder
        .getBean(IAgentUpgradeAO.class);

    private XN627022Req req;

    @Override
    public Object doBusiness() throws BizException {
        agentUpgradeAO.editAgentUpgrade(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627022Req.class);
        StringValidater.validateBlank(req.getAgentCode(), req.getCode(),
            req.getIsCompanyApprove(), req.getIsReset(),
            req.getRecommendNumber(), req.getUpgradeFirstAmount());
        StringValidater.validateNumber(req.getRecommendNumber(),
            req.getUpgradeFirstAmount());
    }

}
