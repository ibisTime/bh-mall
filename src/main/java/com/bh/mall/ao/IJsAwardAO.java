package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.JsAward;
import com.bh.mall.dto.req.XN627241Req;

@Component
public interface IJsAwardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addJsAward(String level, String jsAwardLevel, String percent,
            String updater, String remark);

    public void dropJsAward(String code);

    public void editJsAward(XN627241Req req);

    public Paginable<JsAward> queryJsAwardPage(int start, int limit,
            JsAward condition);

    public List<JsAward> queryJsAwardList(JsAward condition);

    public JsAward getJsAward(String code);

}
