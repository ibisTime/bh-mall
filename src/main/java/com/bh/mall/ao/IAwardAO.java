package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Award;

@Component
public interface IAwardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Award> queryAwardPage(int start, int limit,
            Award condition);

    public List<Award> queryAwardList(Award condition);

    public Award getAward(String code);

    public void editAward(String code, String value1, String value2,
            String value3);

}
