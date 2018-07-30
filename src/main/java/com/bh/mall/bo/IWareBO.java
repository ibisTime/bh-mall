package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Ware;
import com.bh.mall.enums.EBizType;

public interface IWareBO extends IPaginableBO<Ware> {

    public void removeWare(String code);

    public void refreshWare(Ware data);

    public List<Ware> queryWareList(Ware condition);

    public Ware getWare(String code);

    public Ware getWareByProductSpec(String userId,
            String productSpecsCode);

    public List<Ware> getWareByProduct(String productCode);

    void changeWare(String code, Integer quantity, EBizType bizType,
            String fromBizNote, String refNo);

    void transQuantity(String fromUser, String fromSpecs, String toUser,
            String toSpecs, Integer quantity, EBizType fromBizType,
            EBizType toBizType, String fromBizNote, String toBizNote,
            String refNo);

    public void saveWare(Ware data, Integer quantity,
            EBizType bizType, String bizNote, String refNo);

    public void refreshLogCode(Ware whData);

    public List<Ware> getWareByUser(String userId);

    public long getTotalCountByProduct(Ware condition);

    public List<Ware> queryWareProductList(Ware condition);

    public void buyWare(InOrder inOrder, Agent agent);

    public void changeWarePrice(List<Ware> whList, Integer level);

}
