package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.TjAward;

@Component
public interface ITjAwardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<TjAward> queryTjAwardPage(int start, int limit,
            TjAward condition);

    public List<TjAward> queryTjAwardList(TjAward condition);

    public TjAward getTjAward(String code);

    public void editTjAward(String code, String value1, String value2,
            String value3);

}
