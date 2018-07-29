package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.JsAward;

public interface IJsAwardBO extends IPaginableBO<JsAward> {

    public boolean isJsAwardExist(String code);

    public String saveJsAward(JsAward data);

    public void removeJsAward(JsAward data);

    public void refreshJsAward(JsAward data);

    public List<JsAward> queryJsAwardList(JsAward condition);

    public JsAward getJsAward(String code);

    public JsAward getJsAwardByLevel(Integer level);

    public JsAward getJsAwardByLevel(Integer fromLevel, Integer toLevel);

}
