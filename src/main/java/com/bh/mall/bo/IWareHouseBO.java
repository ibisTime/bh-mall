package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.enums.EBizType;

public interface IWareHouseBO extends IPaginableBO<WareHouse> {

    public void removeWareHouse(String code);

    public void refreshWareHouse(WareHouse data);

    public List<WareHouse> queryWareHouseList(WareHouse condition);

    public WareHouse getWareHouse(String code);

    public WareHouse getWareHouseByProductSpec(String userId,
            String productSpecsCode);

    public List<WareHouse> getWareHouseByProduct(String productCode);

    void changeWareHouse(String code, Integer quantity, EBizType bizType,
            String fromBizNote, String refNo);

    void transQuantity(String fromUser, String fromSpecs, String toUser,
            String toSpecs, Integer quantity, EBizType fromBizType,
            EBizType toBizType, String fromBizNote, String toBizNote,
            String refNo);

    public void saveWareHouse(WareHouse data, Integer quantity,
            EBizType bizType, String bizNote, String refNo);

    public void refreshLogCode(WareHouse whData);

    public List<WareHouse> getWareHouseByUser(String userId);

    public long getTotalCountByProduct(WareHouse condition);

    public List<WareHouse> queryWareHousePorductList(WareHouse condition);

    // ===========================================

    public void changeWareHouse(String code, Order data, Integer quantity,
            EBizType izType, String value);

    public WareHouse getWareHouseByUser(String userId, String projectCode);

}
