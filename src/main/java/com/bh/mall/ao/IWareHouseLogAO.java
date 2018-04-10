package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareHouseLog;

@Component
public interface IWareHouseLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<WareHouseLog> queryWareHouseLogPage(int start, int limit,
            WareHouseLog condition);

    public List<WareHouseLog> queryWareHouseLogList(WareHouseLog condition);

    public WareHouseLog getWareHouseLog(String code);

}
