package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SpecsLog;

@Component
public interface ISpecsLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void dropProductLog(String code);

    public void editProductLog(SpecsLog data);

    public Paginable<SpecsLog> queryProductLogPage(int start, int limit,
            SpecsLog condition);

    public List<SpecsLog> queryProductLogList(SpecsLog condition);

    public SpecsLog getProductLog(String code);

}
