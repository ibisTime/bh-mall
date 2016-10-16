package com.std.user.api.impl;

import com.std.user.ao.ICompanyCertificateAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.CompanyCertificate;
import com.std.user.dto.req.XN806141Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/** 
 * 新增公司资质
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN806141 extends AProcessor {
    private ICompanyCertificateAO companyCertificateAO = SpringContextHolder
        .getBean(ICompanyCertificateAO.class);

    private XN806141Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CompanyCertificate data = new CompanyCertificate();
        data.setCode(req.getCode());
        data.setStatus(req.getStatus());
        data.setApproveUser(req.getApproveUser());
        data.setApproveNote(req.getApproveNote());
        int count = companyCertificateAO.editCompanyCertificate(data);
        return new BooleanRes(count > 0 ? true : false);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN806141Req.class);
        StringValidater.validateBlank(req.getCode(), req.getStatus(),
            req.getApproveUser());
    }
}
