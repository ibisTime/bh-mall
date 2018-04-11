package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.domain.WareHouseLog;
import com.bh.mall.enums.EBizType;

public interface IWareHouseLogBO extends IPaginableBO<WareHouseLog> {

    public int removeWareHouseLog(String code);

    public int refreshWareHouseLog(WareHouseLog data);

    public List<WareHouseLog> queryWareHouseLogList(WareHouseLog condition);

    public WareHouseLog getWareHouseLog(String code);

    public String saveWareHouseLog(Order data, WareHouse whData,
            Integer tranNumber, String bizType, String bizNote);

    public String saveWareHouseLog(WareHouse dbData, Integer quantity,
            EBizType bizType, String bizNote, String refNo);

    public String refreshChangePrice(ChangeProduct data, WareHouse dbData,
            Long changePirce, int canChangeQuantity, String status,
            String bizNote);

}
