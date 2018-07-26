package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ICNavigateAO;
import com.bh.mall.bo.ICNavigateBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.CNavigate;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.exception.BizException;

@Service
public class CNavigateAOImpl implements ICNavigateAO {

    @Autowired
    private ICNavigateBO cNavigateBO;

    @Override
    public String addCNavigate(CNavigate data) {
        return cNavigateBO.saveCNavigate(data);
    }

    @Override
    public void dropCNavigate(String code) {
        if (!cNavigateBO.isCNavigateExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        cNavigateBO.removeCNavigate(code);
    }

    @Override
    public void editCNavigate(CNavigate data) {
        CNavigate cNavigate = cNavigateBO.getCNavigate(data.getCode());
        // 1 地方改
        if (EBoolean.YES.getCode().equals(data.getIsCompanyEdit())) {
            // 判断是否地方首次修改地方默认，是则新增，否则修改地方独有
            if (EBoolean.NO.getCode().equals(cNavigate.getContentType())) {
                CNavigate navigate = cNavigate;
                // 为指定新增特殊前缀格式
                String oldCode = data.getCode();
                if (!data.getCode().contains(EGeneratePrefix.DH.getCode())) {
                    navigate.setCode(
                        OrderNoGenerater.generate(oldCode.substring(0, 3)));
                } else {
                    navigate.setCode(null);
                }
                navigate.setName(data.getName());
                navigate.setStatus(data.getStatus());
                navigate.setBelong(data.getCode());

                navigate.setParentCode(data.getParentCode());
                navigate.setLocation(data.getLocation());
                navigate.setOrderNo(data.getOrderNo());
                navigate.setContentType(data.getContentType());
                navigate.setRemark(data.getRemark());
                navigate.setUrl(data.getUrl());
                navigate.setPic(data.getPic());

                cNavigateBO.saveCNavigate(navigate);
            } else {
                // 地方独有修改，属于不变
                data.setBelong(cNavigate.getBelong());
                cNavigateBO.refreshCNavigate(data);
            }
        } else {
            if (!EBoolean.NO.getCode().equals(cNavigate.getContentType())) {
                data.setBelong(cNavigate.getBelong());
            } else {
                if (StringUtils.isBlank(data.getBelong())) {
                    throw new BizException("xn0000", "属于不能为空");
                }
            }
            cNavigateBO.refreshCNavigate(data);
        }
    }

    @Override
    public Paginable<CNavigate> queryCNavigatePage(int start, int limit,
            CNavigate condition) {
        return cNavigateBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CNavigate> queryCNavigateList(CNavigate condition) {
        return cNavigateBO.queryCNavigateList(condition);
    }

    @Override
    public CNavigate getCNavigate(String code) {
        return cNavigateBO.getCNavigate(code);
    }
}
