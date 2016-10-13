package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICBannerAO;
import com.std.user.bo.ICBannerBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.CBanner;
import com.std.user.exception.BizException;

@Service
public class CBannerAOImpl implements ICBannerAO {

    @Autowired
    private ICBannerBO cBannerBO;

    @Override
    public String addCBanner(CBanner data) {
        return cBannerBO.saveCBanner(data);
    }

    @Override
    public int editCBanner(CBanner data) {
        if (!cBannerBO.isCBannerExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cBannerBO.refreshCBanner(data);
    }

    @Override
    public int dropCBanner(String code) {
        if (!cBannerBO.isCBannerExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return cBannerBO.removeCBanner(code);
    }

    @Override
    public Paginable<CBanner> queryCBannerPage(int start, int limit,
            CBanner condition) {
        return cBannerBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CBanner> queryCBannerList(CBanner condition) {
        return cBannerBO.queryCBannerList(condition);
    }

    @Override
    public CBanner getCBanner(String code) {
        return cBannerBO.getCBanner(code);
    }
}
