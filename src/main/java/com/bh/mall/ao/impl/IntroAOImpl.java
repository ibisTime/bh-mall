package com.bh.mall.ao.impl;

import java.util.ArrayList;
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

@Service
public class IntroAOImpl implements IIntroAO {

    @Autowired
    private IIntroBO introBO;

    @Override
    public List<String> addIntro(String level, List<Intro> introList,
            String updater, String remark) {
        List<String> list = new ArrayList<String>();
        for (Intro intro : introList) {
            Intro data = new Intro();
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.Intro.getCode());
            data.setCode(code);
            data.setLevel(intro.getLevel());
            data.setIntroLevel(intro.getIntroLevel());

            data.setPercent(intro.getPercent());
            data.setUpdater(intro.getUpdater());
            Date date = new Date();
            data.setUpdateDatetime(date);
            data.setRemark(intro.getRemark());

            introBO.saveIntro(data);
        }
        return list;
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
        introBO.removeIntro(code);
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
