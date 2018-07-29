package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IChAwardAO;
import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChAward;
import com.bh.mall.dto.req.XN627862Req;
import com.bh.mall.exception.BizException;

//CHECK ��鲢��ע�� 
@Service
public class ChAwardAOImpl implements IChAwardAO {

    @Autowired
    private IChAwardBO chAwardBO;

    @Override
    public String addChAward(String level, Long startAmount, Long endAmount,
            String percent, String updater, String remark) {
        if (endAmount < startAmount) {
            throw new BizException("xn00000", "起始金额不能大于截止金额");
        }

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AwardInterval.getCode());
        ChAward data = new ChAward();
        data.setCode(code);
        data.setLevel(StringValidater.toInteger(level));
        data.setStartAmount(startAmount);

        data.setEndAmount(endAmount);
        data.setPercent(StringValidater.toDouble(percent));
        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);

        data.setRemark(remark);
        chAwardBO.saveChAward(data);
        return code;
    }

    @Override
    public void editChAward(XN627862Req req) {
        Long startAmount = StringValidater.toLong(req.getStartAmount());
        Long endAmount = StringValidater.toLong(req.getEndAmount());
        if (endAmount < startAmount) {
            throw new BizException("xn00000", "起始金额不能大于截止金额");
        }

        ChAward data = chAwardBO.getChAward(req.getCode());
        data.setStartAmount(startAmount);
        data.setEndAmount(endAmount);
        data.setPercent(StringValidater.toDouble(req.getPercent()));

        data.setUpdater(req.getUpdater());
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        chAwardBO.refreshChAward(data);
    }

    @Override
    public void dropChAward(String code) {
        ChAward data = chAwardBO.getChAward(code);
        chAwardBO.removeChAward(data);
    }

    @Override
    public Paginable<ChAward> queryChAwardPage(int start, int limit,
            ChAward condition) {
        return chAwardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ChAward> queryChAwardList(ChAward condition) {
        return chAwardBO.queryChAwardList(condition);
    }

    @Override
    public ChAward getChAward(String code) {
        return chAwardBO.getChAward(code);
    }
}
