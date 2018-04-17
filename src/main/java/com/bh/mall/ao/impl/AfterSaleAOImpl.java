package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAfterSaleAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.Address;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.dto.req.XN627680Req;
import com.bh.mall.dto.req.XN627682Req;
import com.bh.mall.enums.EAfterSaleStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.exception.BizException;

@Service
public class AfterSaleAOImpl implements IAfterSaleAO {

    @Autowired
    private IAfterSaleBO afterSaleBO;

    @Autowired
    private IAddressBO addressBO;

    @Override
    public String addAfterSale(XN627680Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AfterSale.getCode());
        AfterSale data = new AfterSale();
        data.setCode(code);
        data.setRefNo(req.getRefNo());
        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());
        data.setSaleType(req.getType());

        data.setStatus(EAfterSaleStatus.TO_Approve.getCode());
        data.setAddress(req.getAddressCode());
        data.setPic(req.getPic());
        data.setSaleType(req.getType());
        afterSaleBO.saveAfterSale(data);
        return code;
    }

    @Override
    public void editAfterSale(AfterSale data) {
    }

    @Override
    public void dropAfterSale(String code) {
    }

    @Override
    public Paginable<AfterSale> queryAfterSalePage(int start, int limit,
            AfterSale condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return afterSaleBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AfterSale> queryAfterSaleList(AfterSale condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        return afterSaleBO.queryAfterSaleList(condition);
    }

    @Override
    public AfterSale getAfterSale(String code) {
        return afterSaleBO.getAfterSale(code);
    }

    @Override
    public void approvrAfterSale(String code, String approver,
            String approveNote, String result) {
        AfterSale data = afterSaleBO.getAfterSale(code);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setStatus(EAfterSaleStatus.Approve_NO.getCode());
        if (EResult.Result_YES.getCode().equals(result)) {
            data.setStatus(EAfterSaleStatus.Approve_YES.getCode());
        }
        afterSaleBO.approvrAfterSale(data);

    }

    @Override
    public void changeProduct(XN627682Req req) {
        AfterSale data = afterSaleBO.getAfterSale(req.getCode());
        Address aData = addressBO.getAddress(data.getAddress());
        if (EAfterSaleStatus.Approve_NO.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该售后单未通过审核");
        }

        data.setDeliver(req.getDeliver());
        data.setDeliveDatetime(new Date());
        data.setLogisticsCode(req.getLogisticsCompany());
        data.setLogisticsCompany(req.getLogisticsCompany());
        data.setPdf(req.getDeliver());
        data.setStatus(EAfterSaleStatus.Delived.getCode());
        data.setProvince(aData.getProvince());

        data.setCity(aData.getCity());
        data.setArea(aData.getArea());
        data.setAddress(aData.getAddress());
        data.setMobile(aData.getMobile());
        data.setSigner(aData.getUserId());

        afterSaleBO.changeProduct(data);
    }
}
