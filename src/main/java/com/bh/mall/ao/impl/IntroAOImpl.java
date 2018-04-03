package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IIntroAO;
import com.bh.mall.bo.IIntroBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Intro;
import com.bh.mall.exception.BizException;

@Service
public class IntroAOImpl implements IIntroAO {

    @Autowired
    private IIntroBO introBO;

    @Override
    public String addIntro(Intro data) {
        return introBO.saveIntro(data);
    }

    @Override
    public int editIntro(Intro data) {
        if (!introBO.isIntroExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return introBO.refreshIntro(data);
    }

    @Override
    public int dropIntro(String code) {
        if (!introBO.isIntroExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return introBO.removeIntro(code);
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
