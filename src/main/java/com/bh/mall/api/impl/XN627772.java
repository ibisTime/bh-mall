package com.bh.mall.api.impl;

import com.bh.mall.ao.ICompanyChannelAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.CompanyChannel;
import com.bh.mall.dto.req.XN627772Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改公司渠道
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:50:23 
 * @history:
 */
public class XN627772 extends AProcessor {
    private ICompanyChannelAO companyChannelAO = SpringContextHolder
        .getBean(ICompanyChannelAO.class);

    private XN627772Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        CompanyChannel data = new CompanyChannel();
        data.setId(StringValidater.toLong(req.getId()));
        data.setCompanyCode(req.getCompanyCode());
        data.setCompanyName(req.getCompanyName());
        data.setChannelType(req.getChannelType());
        data.setStatus(req.getStatus());
        data.setChannelCompany(req.getChannelCompany());
        data.setPrivateKey1(req.getPrivatekey());
        data.setPageUrl(req.getPageUrl());
        data.setErrorUrl(req.getErrorUrl());
        data.setBackUrl(req.getBackUrl());
        data.setFee(StringValidater.toLong(req.getFee()));
        data.setRemark(req.getRemark());
        companyChannelAO.editCompanyChannel(data);
        return new BooleanRes(true);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627772Req.class);
        StringValidater.validateBlank(req.getId(), req.getCompanyCode(),
            req.getCompanyName(), req.getChannelType(), req.getStatus(),
            req.getChannelCompany(), req.getPrivatekey(), req.getPageUrl(),
            req.getErrorUrl(), req.getBackUrl());
        StringValidater.validateAmount(req.getFee());
    }
}
