package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.TjAward;

@Component
public interface ITjAwardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<TjAward> queryAwardPage(int start, int limit,
            TjAward condition);

    public List<TjAward> queryAwardList(TjAward condition);

    public TjAward getAward(String code);

    public void editAward(String code, String value1, String value2,
            String value3);

}
