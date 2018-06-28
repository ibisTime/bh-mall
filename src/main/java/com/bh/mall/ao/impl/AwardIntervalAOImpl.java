package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAwardIntervalAO;
import com.bh.mall.bo.IAwardIntervalBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AwardInterval;
import com.bh.mall.dto.req.XN627862Req;
import com.bh.mall.exception.BizException;

//CHECK ��鲢��ע�� 
@Service
public class AwardIntervalAOImpl implements IAwardIntervalAO {

    @Autowired
    private IAwardIntervalBO awardIntervalBO;

    @Override
    public String addAwardInterval(String level, Long startAmount,
            Long endAmount, String percent, String updater, String remark) {
        if (endAmount < startAmount) {
            throw new BizException("xn00000", "起始金额不能大于截止金额");
        }

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AwardInterval.getCode());
        AwardInterval data = new AwardInterval();
        data.setCode(code);
        data.setLevel(StringValidater.toInteger(level));
        data.setStartAmount(startAmount);

        data.setEndAmount(endAmount);
        data.setPercent(StringValidater.toDouble(percent));
        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);

        data.setRemark(remark);
        awardIntervalBO.saveAwardInterval(data);
        return code;
    }

    @Override
    public void editAwardInterval(XN627862Req req) {
        Long startAmount = StringValidater.toLong(req.getStartAmount());
        Long endAmount = StringValidater.toLong(req.getEndAmount());
        if (endAmount < startAmount) {
            throw new BizException("xn00000", "起始金额不能大于截止金额");
        }

        AwardInterval data = awardIntervalBO.getAwardInterval(req.getCode());
        data.setStartAmount(startAmount);
        data.setEndAmount(endAmount);
        data.setPercent(StringValidater.toDouble(req.getPercent()));

        data.setUpdater(req.getUpdater());
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        awardIntervalBO.refreshAwardInterval(data);
    }

    @Override
    public void dropAwardInterval(String code) {
        AwardInterval data = awardIntervalBO.getAwardInterval(code);
        awardIntervalBO.removeAwardInterval(data);
    }

    @Override
    public Paginable<AwardInterval> queryAwardIntervalPage(int start, int limit,
            AwardInterval condition) {
        return awardIntervalBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AwardInterval> queryAwardIntervalList(AwardInterval condition) {
        return awardIntervalBO.queryAwardIntervalList(condition);
    }

    @Override
    public AwardInterval getAwardInterval(String code) {
        return awardIntervalBO.getAwardInterval(code);
    }
}
