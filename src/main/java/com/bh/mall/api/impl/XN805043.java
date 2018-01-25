package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805043Req;
import com.bh.mall.dto.res.XN805042Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 申请注册
 * @author: xieyj 
 * @since: 2017年7月14日 下午11:03:23 
 * @history:
 */
public class XN805043 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805043Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805042Res(userAO.doApplyRegUser(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805043Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getKind(),
            req.getCompanyCode(), req.getSystemCode());
    }
}
