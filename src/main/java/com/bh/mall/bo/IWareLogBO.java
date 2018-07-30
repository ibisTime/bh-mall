package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ExchangeOrder;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Ware;
import com.bh.mall.domain.WareLog;
import com.bh.mall.enums.EBizType;

public interface IWareLogBO extends IPaginableBO<WareLog> {

    public List<WareLog> queryWareLogList(WareLog condition);

    public WareLog getWareLog(String code);

    public String saveWareLog(InOrder data, Ware whData, Integer tranNumber,
            String bizType, String bizNote);

    public String saveWareLog(Ware dbData, Integer quantity, EBizType bizType,
            String bizNote, String refNo);

    public String refreshChangePrice(ExchangeOrder data, Ware dbData,
            Long changePirce, int canChangeQuantity, String status,
            String bizNote);

    public String saveWareLog(InOrder inOrder, String wareCode,
            Integer beforeNumber, Integer changeNumber, String bizType,
            String bizNote, String refNo);

}
