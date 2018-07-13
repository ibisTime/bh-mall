package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627815Req;
import com.bh.mall.dto.res.XN627814Res;

@Component
public interface IWareHouseAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<WareHouse> queryWareHousePage(int start, int limit,
            WareHouse condition);

    public List<WareHouse> queryWareHouseList(WareHouse condition);

    public WareHouse getWareHouse(String code);

    public Paginable<WareHouse> queryWareHouseFrontPage(int start, int limit,
            WareHouse condition);

    public XN627814Res getWareHouseByUser(String userId);

    // 云仓提货
    public void deliveProject(XN627815Req req);

    public Paginable<WareHouse> queryWareHouseCFrontPage(int start, int limit,
            WareHouse condition);

    public WareHouse getWareHouseByCustomer(String code);

}
