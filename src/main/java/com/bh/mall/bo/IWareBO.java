package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.Ware;
import com.bh.mall.enums.EWareLogType;

public interface IWareBO extends IPaginableBO<Ware> {

    public void refreshWare(Ware data, String updater, String remark);

    public List<Ware> queryWareList(Ware condition);

    public Ware getWare(String code);

    public Ware getWareByProductSpec(String userId, String productSpecsCode);

    public List<Ware> getWareByProduct(String productCode);

    void changeWare(String code, String type, Integer quantity,
            EWareLogType bizType, String fromBizNote, String refNo);

    public void saveWare(Ware data, String type, Integer quantity,
            EWareLogType bizType, String bizNote, String refNo);

    public void refreshLogCode(Ware whData);

    public List<Ware> getWareByUser(String userId);

    public long getTotalCountByProduct(Ware condition);

    public void buyWare(String orderCode, String productCode,
            String productName, String specsCode, String specsName,
            Integer quantity, Long price, Agent agent, EWareLogType bizType,
            String bizNote);

    public void changeWarePrice(String userId, Integer level);

    public void checkProduct(String toUser, String specsCode);

    public void removeByAgent(String userId);

    public void removeWare(Ware data);

    public List<Ware> getWareByProductList(String code);

}
