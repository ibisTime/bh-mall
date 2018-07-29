package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IIntroBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IIntroDAO;
import com.bh.mall.domain.Intro;
import com.bh.mall.exception.BizException;

@Component
public class IntroBOImpl extends PaginableBOImpl<Intro> implements IIntroBO {

    @Autowired
    private IIntroDAO introDAO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Override
    public boolean isIntroExist(String code) {
        Intro condition = new Intro();
        condition.setCode(code);
        if (introDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveIntro(Intro data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.Intro.getCode());
            data.setCode(code);
            introDAO.insert(data);
        }
        return code;
    }

    @Override
    public void removeIntro(Intro data) {
        introDAO.delete(data);
    }

    @Override
    public void refreshIntro(Intro data) {
        introDAO.update(data);
    }

    @Override
    public List<Intro> queryIntroList(Intro condition) {
        return introDAO.selectList(condition);
    }

    @Override
    public Intro getIntro(String code) {
        Intro data = null;
        if (StringUtils.isNotBlank(code)) {
            Intro condition = new Intro();
            condition.setCode(code);
            data = introDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "介绍奖励不存在");
            }
        }
        return data;
    }

    @Override
    public Intro getIntroByLevel(Integer level) {
        Intro condition = new Intro();
        condition.setLevel(level);
        return introDAO.select(condition);
    }

    @Override
    public Intro getIntroByLevel(Integer fromLevel, Integer toLevel) {
        Intro condition = new Intro();
        condition.setLevel(fromLevel);
        condition.setLevel(toLevel);
        Intro data = introDAO.select(condition);
        return data;
    }
}
