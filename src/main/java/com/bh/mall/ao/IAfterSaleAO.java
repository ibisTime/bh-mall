package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.dto.req.XN627680Req;
import com.bh.mall.dto.req.XN627682Req;

@Component
public interface IAfterSaleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addAfterSale(XN627680Req req);

    public void dropAfterSale(String code);

    public void editAfterSale(AfterSale data);

    public Paginable<AfterSale> queryAfterSalePage(int start, int limit,
            AfterSale condition);

    public List<AfterSale> queryAfterSaleList(AfterSale condition);

    public AfterSale getAfterSale(String code);

    public void approvrAfterSale(String code, String approver,
            String approveNote, String result);

    public void changeProduct(XN627682Req req);

}
