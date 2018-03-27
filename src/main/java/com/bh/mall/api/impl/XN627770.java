package com.bh.mall.api.impl;

import com.bh.mall.ao.ICompanyChannelAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.CompanyChannel;
import com.bh.mall.dto.req.XN627770Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增公司渠道
 * @author: xieyj 
 * @since: 2016年11月11日 下午3:18:19 
 * @history:
 */
public class XN627770 extends AProcessor {
    private ICompanyChannelAO companyChannelAO = SpringContextHolder
        .getBean(ICompanyChannelAO.class);

    private XN627770Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        CompanyChannel data = new CompanyChannel();
        data.setCompanyCode(req.getCompanyCode());
        data.setCompanyName(req.getCompanyName());
        data.setChannelType(req.getChannelType());
        data.setStatus(req.getStatus());
        data.setChannelCompany(req.getPaycompany());
        data.setPrivateKey1(req.getPrivatekey());
        data.setPageUrl(req.getPageUrl());
        data.setErrorUrl(req.getErrorUrl());
        data.setBackUrl(req.getBackUrl());
        data.setFee(StringValidater.toLong(req.getFee()));
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        companyChannelAO.addCompanyChannel(data);
        return new BooleanRes(true);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627770Req.class);
        ObjValidater.validateReq(req);
    }
}
