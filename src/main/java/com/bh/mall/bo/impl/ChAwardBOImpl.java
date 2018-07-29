package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IChAwardBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IChAwardDAO;
import com.bh.mall.domain.ChAward;
import com.bh.mall.exception.BizException;

@Component
public class ChAwardBOImpl extends PaginableBOImpl<ChAward>
        implements IChAwardBO {

    @Autowired
    private IChAwardDAO chAwardDAO;

    public void saveChAward(ChAward data) {
        chAwardDAO.insert(data);
    }

    @Override
    public void removeChAward(ChAward data) {
        chAwardDAO.delete(data);
    }

    @Override
    public void refreshChAward(ChAward data) {
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
}
