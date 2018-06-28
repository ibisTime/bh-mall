package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IIntroAO;
import com.bh.mall.bo.IIntroBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Intro;
import com.bh.mall.dto.req.XN627241Req;
import com.bh.mall.exception.BizException;

@Service
public class IntroAOImpl implements IIntroAO {

    @Autowired
    private IIntroBO introBO;

    @Override
    public String addIntro(String level, String introLevel, String percent,
            String updater, String remark) {
        if (StringValidater.toInteger(introLevel) >= StringValidater
            .toInteger(level)) {
            throw new BizException("xn00000", "可介绍的等级不能低于本等级");
        }

        Intro data = new Intro();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Intro.getCode());
        data.setCode(code);
        data.setLevel(StringValidater.toInteger(level));
        data.setIntroLevel(StringValidater.toInteger(introLevel));

        data.setPercent(StringValidater.toDouble(percent));
        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(remark);
        introBO.saveIntro(data);

        return code;
    }

    @Override
    public void editIntro(XN627241Req req) {
        Intro data = introBO.getIntro(req.getCode());
        data.setPercent(StringValidater.toDouble(req.getPercent()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        introBO.refreshIntro(data);
    }

    @Override
    public void dropIntro(String code) {
        Intro data = introBO.getIntro(code);
        introBO.removeIntro(data);
    }

    @Override
    public Paginable<Intro> queryIntroPage(int start, int limit,
            Intro condition) {
        return introBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Intro> queryIntroList(Intro condition) {
        return introBO.queryIntroList(condition);
    }

    @Override
    public Intro getIntro(String code) {
        return introBO.getIntro(code);
    }
}
