package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICNavigateAO;
import com.std.user.bo.ICNavigateBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.CNavigate;
import com.std.user.enums.ECNavigateStatus;
import com.std.user.exception.BizException;

@Service
public class CNavigateAOImpl implements ICNavigateAO {

    @Autowired
    private ICNavigateBO cNavigateBO;

    @Override
    public String addCNavigate(CNavigate data) {
        return cNavigateBO.saveCNavigate(data);
    }

    @Override
    public int editCNavigate(CNavigate data) {
        if (!cNavigateBO.isCNavigateExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cNavigateBO.refreshCNavigate(data);
    }

    @Override
    public int editCNavigateStatus(String code) {
        if (!cNavigateBO.isCNavigateExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        CNavigate menu = cNavigateBO.getCNavigate(code);
        CNavigate data = new CNavigate();
        data.setCode(code);
        if (ECNavigateStatus.APPROVE_NO.getCode().equalsIgnoreCase(
            menu.getStatus())) {
            data.setStatus(ECNavigateStatus.APPROVE_YES.getCode());
        } else {
            data.setStatus(ECNavigateStatus.APPROVE_NO.getCode());
        }
        return cNavigateBO.refreshCNavigateStatus(data);
    }

    @Override
    public int dropCNavigate(String code) {
        if (!cNavigateBO.isCNavigateExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cNavigateBO.removeCNavigate(code);
    }

    @Override
    public Paginable<CNavigate> queryCNavigatePage(int start, int limit,
            CNavigate condition) {
        return cNavigateBO.getPaginable(start, limit, condition);
    }

    @Override
    public Paginable<CNavigate> queryCNavigatePageCSW(int start, int limit,
            CNavigate condition) {
        Paginable<CNavigate> page = cNavigateBO.getPaginable(start, limit,
            condition);
        List<CNavigate> list = page.getList();
        for (CNavigate cNavigate : list) {
            if (cNavigate.getBelong().contains("DH")) {
                list.remove(cNavigate);
            }
        }
        return page;
    }

    @Override
    public List<CNavigate> queryCNavigateList(CNavigate condition) {
        return cNavigateBO.queryCNavigateList(condition);
    }

    @Override
    public CNavigate getCNavigate(String code) {
        return cNavigateBO.getCNavigate(code);
    }

    @Override
    public int editCNavigateCSW(CNavigate data) {
        if (!cNavigateBO.isCNavigateExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cNavigateBO.refreshCNavigateCSW(data);
    }
}
