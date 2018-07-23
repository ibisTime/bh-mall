package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.BUser;
import com.bh.mall.dto.req.XN627352Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理结构
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627352 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627352Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BUser condition = new BUser();
        condition.setKeyWord(req.getKeyword());
        condition.setHighUserId(req.getUserId());
        return userAO.queryAgentPage(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627352Req.class);
        ObjValidater.validateReq(req);
    }

}
