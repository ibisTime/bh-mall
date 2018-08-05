package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IJsAwardAO;
import com.bh.mall.bo.IJsAwardBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.JsAward;
import com.bh.mall.dto.req.XN627242Req;
import com.bh.mall.exception.BizException;

@Service
public class JsAwardAOImpl implements IJsAwardAO {

    @Autowired
    private IJsAwardBO jsAwardBO;

    @Override
    public String addJsAward(String level, String introLevel, String percent,
            String updater, String remark) {
        if (StringValidater.toInteger(introLevel) >= StringValidater
            .toInteger(level)) {
            throw new BizException("xn00000", "可介绍的等级不能低于本等级");
        }
        jsAwardBO.isJsAwardExist(level, introLevel);

        return jsAwardBO.saveJsAward(StringValidater.toInteger(level),
            StringValidater.toInteger(introLevel),
            StringValidater.toDouble(percent), updater, remark);

    }

    @Override
    public void editJsAward(XN627242Req req) {
        JsAward data = jsAwardBO.getJsAward(req.getCode());

        jsAwardBO.refreshJsAward(data, req.getPercent(), req.getUpdater(),
            req.getRemark());
    }

    @Override
    public void dropJsAward(String code) {
        JsAward data = jsAwardBO.getJsAward(code);
        jsAwardBO.removeJsAward(data);
    }

    @Override
    public Paginable<JsAward> queryJsAwardPage(int start, int limit,
            JsAward condition) {
        return jsAwardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<JsAward> queryJsAwardList(JsAward condition) {
        return jsAwardBO.queryJsAwardList(condition);
    }

    @Override
    public JsAward getJsAward(String code) {
        return jsAwardBO.getJsAward(code);
    }
}
