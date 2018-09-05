package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IChAwardDAO;
import com.bh.mall.domain.ChAward;
import com.bh.mall.exception.BizException;

@Component
public class ChAwardBOImpl extends PaginableBOImpl<ChAward>
        implements IChAwardBO {

    @Autowired
    private IChAwardDAO chAwardDAO;

    public String saveChAward(String level, Long startAmount, Long endAmount,
            String percent, String updater, String remark) {
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
        chAwardDAO.insert(data);
        return code;
    }

    @Override
    public void removeChAward(ChAward data) {
        chAwardDAO.delete(data);
    }

    @Override
    public void refreshChAward(ChAward data, Long startAmount, Long endAmount,
            String percent, String updater, String remark) {
        data.setStartAmount(startAmount);
        data.setEndAmount(endAmount);
        data.setPercent(StringValidater.toDouble(percent));

        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(remark);
        chAwardDAO.update(data);
    }

    @Override
    public List<ChAward> queryChAwardList(ChAward condition) {
        return chAwardDAO.selectList(condition);
    }

    @Override
    public ChAward getChAward(String code) {
        ChAward data = null;
        if (StringUtils.isNotBlank(code)) {
            ChAward condition = new ChAward();
            condition.setCode(code);
            data = chAwardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "介绍奖励不存在");
            }
        }
        return data;
    }

    @Override
    public ChAward getChAwardByLevel(Integer level, Long amount) {
        ChAward condition = new ChAward();
        condition.setLevel(level);
        condition.setAmount(amount);
        return chAwardDAO.select(condition);
    }

    @Override
    public ChAward isChAwardExist(Integer level, Long startAmount) {
        ChAward condition = new ChAward();
        condition.setLevel(level);
        condition.setAmount(startAmount);
        return chAwardDAO.select(condition);
    }

    @Override
    public List<ChAward> getChAwardByLevel(Integer level) {
        ChAward condition = new ChAward();
        condition.setLevel(level);
        return chAwardDAO.selectListByLevel(condition);
    }

}
