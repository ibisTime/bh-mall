package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627815Req;
import com.bh.mall.dto.res.XN627814Res;

@Component
public interface IWareAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Ware> queryWarePage(int start, int limit,
            Ware condition);

    public List<Ware> queryWareList(Ware condition);

    public Ware getWare(String code);

    public Paginable<Ware> queryWareFrontPage(int start, int limit,
            Ware condition);

    public XN627814Res getWareByUser(String userId);

    // 云仓提货
    public void deliveProject(XN627815Req req);

    public Paginable<Ware> queryWareCFrontPage(int start, int limit,
            Ware condition);

    public Ware getWareByCustomer(String code);

}
