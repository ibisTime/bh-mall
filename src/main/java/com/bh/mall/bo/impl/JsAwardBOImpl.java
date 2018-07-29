package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IJsAwardBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IJsAwardDAO;
import com.bh.mall.domain.JsAward;
import com.bh.mall.exception.BizException;

@Component
public class JsAwardBOImpl extends PaginableBOImpl<JsAward> implements IJsAwardBO {

    @Autowired
    private IJsAwardDAO jsAwardDAO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Override
    public boolean isJsAwardExist(String code) {
        JsAward condition = new JsAward();
        condition.setCode(code);
        if (jsAwardDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveJsAward(JsAward data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.Intro.getCode());
            data.setCode(code);
            jsAwardDAO.insert(data);
        }
        return code;
    }

    @Override
    public void removeJsAward(JsAward data) {
        jsAwardDAO.delete(data);
    }

    @Override
    public void refreshJsAward(JsAward data) {
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
        condition.setLevel(toLevel);
        JsAward data = jsAwardDAO.select(condition);
        return data;
    }
}
