package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.JsAward;

public interface IJsAwardBO extends IPaginableBO<JsAward> {

    public void isJsAwardExist(String level, String introLevel);

    public String saveJsAward(Integer level, Integer introLevel, Double percent,
            String updater, String remark);

    public void removeJsAward(JsAward data);

    public void refreshJsAward(JsAward data, String percent, String updater,
            String remark);

    public List<JsAward> queryJsAwardList(JsAward condition);

    public JsAward getJsAward(String code);

    public JsAward getJsAwardByLevel(Integer level);

    public JsAward getJsAwardByLevel(Integer fromLevel, Integer toLevel);

}
