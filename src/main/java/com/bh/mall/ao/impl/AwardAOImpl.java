package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAwardAO;
import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Award;

@Service
public class AwardAOImpl implements IAwardAO {

    @Autowired
    private IAwardBO awardBO;

    @Override
    public Paginable<Award> queryAwardPage(int start, int limit,
            Award condition) {
        return awardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Award> queryAwardList(Award condition) {
        return awardBO.queryAwardList(condition);
    }

    @Override
    public Award getAward(String code) {
        return awardBO.getAward(code);
    }

    @Override
    public void editAward(String code, String value1, String value2,
            String value3) {
        Award data = awardBO.getAward(code);
        data.setValue1(StringValidater.toDouble(value1));
        data.setValue2(StringValidater.toDouble(value2));
        data.setValue3(StringValidater.toDouble(value3));
        awardBO.refreshAward(data);
    }

}
