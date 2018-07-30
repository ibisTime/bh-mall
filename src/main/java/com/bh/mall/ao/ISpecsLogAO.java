package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SpecsLog;

@Component
public interface ISpecsLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void dropSpecstLog(String code);

    public void editSpecsLog(SpecsLog data);

    public Paginable<SpecsLog> querySpecsLogPage(int start, int limit,
            SpecsLog condition);

    public List<SpecsLog> querySpecsLogList(SpecsLog condition);

    public SpecsLog getSpecsLog(String code);

}
