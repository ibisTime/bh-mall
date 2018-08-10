package com.bh.mall.api.impl;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627257Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改意向代理资料
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627257 extends AProcessor {

    private IYxFormAO yxForm = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627257Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        yxForm.editYxForm(req.getUserId(), req.getRealName(), req.getWxId(),
            req.getMobile(), StringValidater.toInteger(req.getApplyLevel()),
            req.getProvince(), req.getCity(), req.getArea(), req.getAddress());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627257Req.class);
        ObjValidater.validateReq(req);
    }

}
