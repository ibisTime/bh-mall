package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.WareHouseSpecs;

public interface IWareHouseSpecsBO extends IPaginableBO<WareHouseSpecs> {

    public void saveWareHouseSpecs(WareHouseSpecs data);

    public void removeWareHouseSpecs(String code);

    public void refreshWareHouseSpecs(String code, Integer quantity);

    public List<WareHouseSpecs> queryWareHouseSpecsList(
            WareHouseSpecs condition);

    public WareHouseSpecs getWareHouseSpecs(String code);

    public WareHouseSpecs getWareHouseSpecsByCode(String whCode,
            String productSpecsCode);

    public void saveWareHouseSpecs(String code, Order data);

}
