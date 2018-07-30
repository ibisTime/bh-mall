package com.bh.mall.api.impl;

import com.bh.mall.ao.ITjAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.TjAward;
import com.bh.mall.dto.req.XN627591Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询奖励
 * @author: nyc 
 * @since: 2018年3月26日 上午11:07:48 
 * @history:
 */
public class XN627591 extends AProcessor {

    private ITjAwardAO awardAO = SpringContextHolder.getBean(ITjAwardAO.class);

    private XN627591Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        TjAward condition = new TjAward();
        condition.setType(req.getType());
        condition.setProductCode(req.getProductCode());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        return awardAO.queryTjAwardList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627591Req.class);
        ObjValidater.validateReq(req);

    }

}
