package com.xn.sdhh.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.sdhh.ao.IBusinessAO;
import com.xn.sdhh.bo.IBusinessBO;
import com.xn.sdhh.bo.base.Paginable;
import com.xn.sdhh.core.EGeneratePrefix;
import com.xn.sdhh.core.OrderNoGenerater;
import com.xn.sdhh.domain.Business;
import com.xn.sdhh.dto.req.XN627130Req;

@Service
public class BusinessAOImpl implements IBusinessAO {

    @Autowired
    IBusinessBO businessBO;

    @Override
    public String addBusiness(XN627130Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.BUSINESS.getCode());
        Business data = new Business();
        data.setCode(code);
        data.setQyfzrmc(req.getQyfzrmc());

        return null;
    }

    @Override
    public void editCashier() {

    }

    @Override
    public void editAccountant() {
    }

    @Override
    public void editLoan() {

    }

    @Override
    public Paginable<Business> queryBusinessPage(Business condition, int start,
            int limit) {
        return businessBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Business> queryBusinessList(Business condition) {
        return businessBO.queryBusinessList(condition);
    }

    @Override
    public Business getBusiness(String code) {
        return businessBO.getBusiness(code);
    }

}
