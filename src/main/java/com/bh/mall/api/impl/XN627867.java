package com.bh.mall.api.impl;

import com.bh.mall.ao.IChAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.dto.req.XN627867Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询介绍奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:55:07 
 * @history:
 */
public class XN627867 extends AProcessor {

    private IChAwardAO chAwardAO = SpringContextHolder
        .getBean(IChAwardAO.class);

    private XN627867Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return chAwardAO.getChAward(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627867Req.class);
    }

}
