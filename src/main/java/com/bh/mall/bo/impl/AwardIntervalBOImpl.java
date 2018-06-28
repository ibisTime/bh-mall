package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAwardIntervalBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAwardIntervalDAO;
import com.bh.mall.domain.AwardInterval;
import com.bh.mall.exception.BizException;

@Component
public class AwardIntervalBOImpl extends PaginableBOImpl<AwardInterval>
        implements IAwardIntervalBO {

    @Autowired
    private IAwardIntervalDAO awardIntervalDAO;

    public void saveAwardInterval(AwardInterval data) {
        awardIntervalDAO.insert(data);
    }

    @Override
    public void removeAwardInterval(AwardInterval data) {
        awardIntervalDAO.delete(data);
    }

    @Override
    public void refreshAwardInterval(AwardInterval data) {
        awardIntervalDAO.update(data);
    }

    @Override
    public List<AwardInterval> queryAwardIntervalList(AwardInterval condition) {
        return awardIntervalDAO.selectList(condition);
    }

    @Override
    public AwardInterval getAwardInterval(String code) {
        AwardInterval data = null;
        if (StringUtils.isNotBlank(code)) {
            AwardInterval condition = new AwardInterval();
            condition.setCode(code);
            data = awardIntervalDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "介绍奖励不存在");
            }
        }
        return data;
    }
}
