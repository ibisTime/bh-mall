package com.bh.mall.api.impl;

import com.bh.mall.ao.ISignLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805148Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 获取连续签到次数
 * @author: xieyj 
 * @since: 2017年2月22日 上午10:47:29 
 * @history:
 */
public class XN805148 extends AProcessor {

    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805148Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return signLogAO.getTodaySignDays(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805148Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}
