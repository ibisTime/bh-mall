package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.WareHouse;

public interface IWareHouseBO extends IPaginableBO<WareHouse> {

    public void saveWareHouse(WareHouse data, String logCode);

    public void removeWareHouse(String code);

    public void refreshWareHouse(WareHouse data);

    public List<WareHouse> queryWareHouseList(WareHouse condition);

    public WareHouse getWareHouse(String userId);

    public WareHouse getWareHouseByProductSpec(String userId,
            String productSpecsCode);

    public WareHouse getWareHouseByProduct(String userId);

}
