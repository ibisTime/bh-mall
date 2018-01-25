package com.bh.mall.api.impl;

import com.bh.mall.ao.ICNavigateAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.api.converter.CNavigateConverter;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.CNavigate;
import com.bh.mall.dto.req.XN805802Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 修改导航
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN805802 extends AProcessor {
    private ICNavigateAO cNavigateAO = SpringContextHolder
        .getBean(ICNavigateAO.class);

    private XN805802Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CNavigate data = CNavigateConverter.converter(req);
        cNavigateAO.editCNavigate(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805802Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getType(), req.getStatus(), req.getLocation(),
            req.getOrderNo(), req.getBelong(), req.getCompanyCode());
    }
}
