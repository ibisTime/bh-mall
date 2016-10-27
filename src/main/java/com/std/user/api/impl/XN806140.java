package com.std.user.api.impl;

import com.std.user.ao.ICompanyCertificateAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.CompanyCertificate;
import com.std.user.dto.req.XN806140Req;
import com.std.user.dto.res.PKCodeRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 新增公司资质
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806140 extends AProcessor {
    private ICompanyCertificateAO companyCertificateAO = SpringContextHolder
        .getBean(ICompanyCertificateAO.class);

    private XN806140Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CompanyCertificate data = new CompanyCertificate();
        data.setCertificateCode(req.getCertificateCode());
        data.setCompanyCode(req.getCompanyCode());
        data.setApplyUser(req.getApplyUser());
        String code = companyCertificateAO.addCompanyCertificate(data);
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806140Req.class);
        StringValidater.validateBlank(req.getCertificateCode(),
            req.getCompanyCode());
    }
}
