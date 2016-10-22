package com.std.user.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICNavigateAO;
import com.std.user.bo.ICNavigateBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.CNavigate;
import com.std.user.enums.EBoolean;
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
        List<CNavigate> newList = new ArrayList<CNavigate>();
        for (CNavigate cNavigate : list) {
            if (cNavigate.getBelong().contains("DH")) {
                for (CNavigate cNavigate1 : list) {
                    if (cNavigate1.getBelong().equals(cNavigate.getBelong())) {
                        newList.add(cNavigate);
                    }
                }
            }
        }
        list.removeAll(newList);
        return page;
    }

    @Override
    public List<CNavigate> queryCNavigateList(CNavigate condition) {
        return cNavigateBO.queryCNavigateList(condition);
    }

    @Override
    public List<CNavigate> queryCNavigateListCSW(CNavigate condition) {
        List<CNavigate> list = cNavigateBO.queryCNavigateList(condition);
        List<CNavigate> newList = new ArrayList<CNavigate>();
        for (CNavigate cNavigate : list) {
            if (cNavigate.getBelong().contains("DH")) {
                for (CNavigate dfNavigate : list) {
                    if (dfNavigate.getCode().equals(cNavigate.getBelong())) {
                        newList.add(dfNavigate);
                    }
                }
            }
        }
        list.removeAll(newList);
        return list;
    }

    @Override
    public CNavigate getCNavigate(String code) {
        return cNavigateBO.getCNavigate(code);
    }

    @Override
    public void editCNavigateCSW(CNavigate data) {
        CNavigate cNavigate = cNavigateBO.getCNavigate(data.getCode());
        // 判断是全局地方默认或者地方独有，全局和地方默认companyCode=0
        if (!EBoolean.NO.getCode().equals(data.getCompanyCode())) {
            // 判断是否地方首次修改地方默认，是则新增，否则修改地方独有
            if (EBoolean.NO.getCode().equals(cNavigate.getCompanyCode())) {
                CNavigate navigate = cNavigate;
                navigate.setCode(null);
                navigate.setBelong(data.getCode());
                navigate.setCompanyCode(data.getCompanyCode());
                navigate.setName(data.getName());
                navigate.setUrl(data.getUrl());
                navigate.setPic(data.getPic());
                navigate.setOrderNo(data.getOrderNo());
                cNavigateBO.saveCNavigate(navigate);
            } else {
                // 地方独有修改，属于不变
                data.setBelong(cNavigate.getBelong());
                cNavigateBO.refreshCNavigateCSW(data);
            }
        } else {
            if (StringUtils.isBlank(data.getBelong())) {
                throw new BizException("xn0000", "属于不能为空");
            }
            cNavigateBO.refreshCNavigateCSW(data);
        }
    }
}
