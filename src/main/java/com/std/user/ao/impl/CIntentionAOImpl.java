package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICIntentionAO;
import com.std.user.bo.ICIntentionBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.CIntention;
import com.std.user.exception.BizException;

@Service
public class CIntentionAOImpl implements ICIntentionAO {

    @Autowired
    private ICIntentionBO cIntentionBO;

    @Override
    public String addCIntention(CIntention data) {
        return cIntentionBO.saveCIntention(data);
    }

    @Override
    public int editCIntention(CIntention data) {
        if (!cIntentionBO.isCIntentionExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cIntentionBO.refreshCIntention(data);
    }

    @Override
    public int dropCIntention(String code) {
        if (!cIntentionBO.isCIntentionExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cIntentionBO.removeCIntention(code);
    }

    @Override
    public Paginable<CIntention> queryCIntentionPage(int start, int limit,
            CIntention condition) {
        return cIntentionBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CIntention> queryCIntentionList(CIntention condition) {
        return cIntentionBO.queryCIntentionList(condition);
    }

    @Override
    public CIntention getCIntention(String code) {
        return cIntentionBO.getCIntention(code);
    }
}
