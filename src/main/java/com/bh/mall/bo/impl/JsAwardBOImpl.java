package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IJsAwardBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IJsAwardDAO;
import com.bh.mall.domain.JsAward;
import com.bh.mall.exception.BizException;

@Component
public class JsAwardBOImpl extends PaginableBOImpl<JsAward>
        implements IJsAwardBO {

    @Autowired
    private IJsAwardDAO jsAwardDAO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Override
    public void isJsAwardExist(String level, String introLevel) {
        JsAward data = null;
        if (StringUtils.isNotBlank(level)
                && StringUtils.isNotBlank(introLevel)) {
            JsAward condition = new JsAward();
            condition.setLevel(StringValidater.toInteger(level));
            condition.setIntroLevel(StringValidater.toInteger(introLevel));
            data = jsAwardDAO.select(condition);
            if (null != data) {
                throw new BizException("xn00000", "该介绍奖已经存在了");
            }
        }
    }

    @Override
    public String saveJsAward(Integer level, Integer introLevel, Double percent,
            String updater, String remark) {
        JsAward data = new JsAward();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Intro.getCode());
        data.setCode(code);
        data.setLevel(level);
        data.setIntroLevel(introLevel);

        data.setPercent(percent);
        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(remark);

        jsAwardDAO.insert(data);
        return code;
    }

    @Override
    public void removeJsAward(JsAward data) {
        jsAwardDAO.delete(data);
    }

    @Override
    public void refreshJsAward(JsAward data, String percent, String updater,
            String remark) {
        data.setPercent(StringValidater.toDouble(percent));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        jsAwardDAO.update(data);
    }

    @Override
    public List<JsAward> queryJsAwardList(JsAward condition) {
        return jsAwardDAO.selectList(condition);
    }

    @Override
    public JsAward getJsAward(String code) {
        JsAward data = null;
        if (StringUtils.isNotBlank(code)) {
            JsAward condition = new JsAward();
            condition.setCode(code);
            data = jsAwardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "介绍奖励不存在");
            }
        }
        return data;
    }

    @Override
    public JsAward getJsAwardByLevel(Integer level) {
        JsAward condition = new JsAward();
        condition.setLevel(level);
        return jsAwardDAO.select(condition);
    }

    @Override
    public JsAward getJsAwardByLevel(Integer fromLevel, Integer toLevel) {
        JsAward condition = new JsAward();
        condition.setLevel(fromLevel);
        condition.setIntroLevel(toLevel);
        JsAward data = jsAwardDAO.select(condition);
        return data;
    }
}
