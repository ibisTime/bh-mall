package com.bh.mall.api.impl;

import com.bh.mall.ao.IIntroAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627240Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增介绍奖
 * @author: nyc 
 * @since: 2018年6月28日 下午4:27:01 
 * @history:
 */
public class XN627240 extends AProcessor {
    private IIntroAO introAO = SpringContextHolder.getBean(IIntroAO.class);

    private XN627240Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return introAO.addIntro(req.getLevel(), req.getIntroLevel(),
            req.getPercent(), req.getUpdater(), req.getRemark());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627240Req.class);
        ObjValidater.validateReq(req);
    }

}
