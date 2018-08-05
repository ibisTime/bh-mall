package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IChAwardAO;
import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChAward;
import com.bh.mall.dto.req.XN627862Req;
import com.bh.mall.exception.BizException;

@Service
public class ChAwardAOImpl implements IChAwardAO {

    @Autowired
    private IChAwardBO chAwardBO;

    @Override
    public String addChAward(String level, Long startAmount, Long endAmount,
            String percent, String updater, String remark) {
        if (endAmount <= startAmount) {
            throw new BizException("xn00000", "起始金额不能大于截止金额");
        }
        // 校验是否存在
        if (chAwardBO.isChAwardExist(StringValidater.toInteger(level),
            startAmount)
                || chAwardBO.isChAwardExist(StringValidater.toInteger(level),
                    endAmount)) {
            throw new BizException("xn00000", "该区间的出货奖已存在了");
        }

        return chAwardBO.saveChAward(level, startAmount, endAmount, percent,
            updater, remark);

    }

    @Override
    public void editChAward(XN627862Req req) {
        Long startAmount = StringValidater.toLong(req.getStartAmount());
        Long endAmount = StringValidater.toLong(req.getEndAmount());
        if (endAmount <= startAmount) {
            throw new BizException("xn00000", "起始金额不能大于截止金额");
        }
        ChAward data = chAwardBO.getChAward(req.getCode());
        // 判断起始金额是否被改变
        if (StringValidater.toLong(req.getStartAmount()) == data
            .getStartAmount()) {
            if (chAwardBO.isChAwardExist(data.getLevel(), startAmount)) {
                throw new BizException("xn00000", "该区间的出货奖已存在了");
            }
        }

        chAwardBO.refreshChAward(data, startAmount, endAmount, req.getPercent(),
            req.getUpdater(), req.getRemark());
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
