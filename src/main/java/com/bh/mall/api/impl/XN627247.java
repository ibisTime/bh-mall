package com.bh.mall.api.impl;

import com.bh.mall.ao.IIntroAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627247Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询介绍奖励
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:33:35 
 * @history:
 */
public class XN627247 extends AProcessor {
    private IIntroAO introAO = SpringContextHolder.getBean(IIntroAO.class);

    private XN627247Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return introAO.getIntro(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627247Req.class);
        ObjValidater.validateReq(req);
    }

}
