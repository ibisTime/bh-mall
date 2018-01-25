package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805042Req;
import com.bh.mall.dto.res.XN805042Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 管理端代注册
 * @author: myb858 
 * @since: 2015年11月10日 下午12:59:10 
 * @history:
 */
public class XN805042 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805042Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805042Res(userAO.doAddUser(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805042Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getKind(),
            req.getUpdater(), req.getCompanyCode(), req.getSystemCode());
    }
}
