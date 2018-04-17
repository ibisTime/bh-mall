package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareHouse;

@Component
public interface IWareHouseAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWareHouse(WareHouse data);

    public void dropWareHouse(String code);

    public void editWareHouse(WareHouse data);

    public Paginable<WareHouse> queryWareHousePage(int start, int limit,
            WareHouse condition);

    public List<WareHouse> queryWareHouseList(WareHouse condition);

    public WareHouse getWareHouse(String code);

    public Paginable<WareHouse> queryWareHouseFrontPage(int start, int limit,
            WareHouse condition);

    public WareHouse getWareHouseByUser(String userId);

}
