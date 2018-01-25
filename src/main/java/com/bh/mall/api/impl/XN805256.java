package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805256Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询用户详情(城市网前端_无需登录)
 * @author: xieyj 
 * @since: 2017年1月10日 下午8:29:26 
 * @history:
 */
public class XN805256 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805256Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doGetUser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805256Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}
