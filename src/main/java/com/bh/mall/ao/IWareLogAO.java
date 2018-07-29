package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareLog;

@Component
public interface IWareLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<WareLog> queryWareLogPage(int start, int limit,
            WareLog condition);

    public List<WareLog> queryWareLogList(WareLog condition);

    public WareLog getWareLog(String code);

}
