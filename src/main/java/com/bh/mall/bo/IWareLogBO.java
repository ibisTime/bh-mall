package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Ware;
import com.bh.mall.domain.WareLog;
import com.bh.mall.enums.EBizType;

public interface IWareLogBO extends IPaginableBO<WareLog> {

    public List<WareLog> queryWareLogList(WareLog condition);

    public WareLog getWareLog(String code);

    public String saveWareLog(Ware dbData, String type, Integer quantity,
            EBizType bizType, String bizNote, String refNo);

}
