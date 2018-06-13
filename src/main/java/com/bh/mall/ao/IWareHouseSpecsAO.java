package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareHouseSpecs;

@Component
public interface IWareHouseSpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWareHouseSpecs(WareHouseSpecs data);

    public void dropWareHouseSpecs(String code);

    public void editWareHouseSpecs(WareHouseSpecs data);

    public Paginable<WareHouseSpecs> queryWareHouseSpecsPage(int start,
            int limit, WareHouseSpecs condition);

    public List<WareHouseSpecs> queryWareHouseSpecsList(
            WareHouseSpecs condition);

    public WareHouseSpecs getWareHouseSpecs(String code);

}
