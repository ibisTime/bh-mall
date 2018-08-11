package com.bh.mall.api.impl;

import com.bh.mall.ao.ICUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.dto.req.XN627347Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询C端用户
 * @author: nyc 
 * @since: 2018年8月11日 下午6:34:30 
 * @history:
 */
public class XN627347 extends AProcessor {
    private ICUserAO userAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN627347Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.getCuser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627347Req.class);
    }
}
