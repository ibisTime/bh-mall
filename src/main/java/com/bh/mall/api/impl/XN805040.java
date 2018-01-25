package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805040Req;
import com.bh.mall.dto.res.XN805040Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 检查手机号是否存在
 * @author: myb858 
 * @since: 2016年1月24日 下午8:23:23 
 * @history:
 */
public class XN805040 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805040Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doCheckMobile(req.getMobile(), req.getKind(),
            req.getCompanyCode(), req.getSystemCode());
        return new XN805040Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805040Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getKind(),
            req.getCompanyCode(), req.getSystemCode());
    }
}
