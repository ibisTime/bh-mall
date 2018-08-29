package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627815Req;
import com.bh.mall.dto.res.XN627805Res;
import com.bh.mall.dto.res.XN627814Res;

/**
 * 云仓
 * @author: clockorange 
 * @since: Jul 31, 2018 4:47:14 PM 
 * @history:
 */
public interface IWareAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 云仓提货
    public void deliveProject(XN627815Req req);

    // 分页查询
    public Paginable<Ware> queryWarePage(int start, int limit, Ware condition);

    // 列表查询
    public List<Ware> queryWareList(Ware condition);

    // 详细查询
    public Ware getWare(String code);

    // 代理查询云仓
    public Paginable<Ware> queryWareFrontPage(int start, int limit,
            Ware condition);

    // 根据代理查询
    public XN627814Res getWareByUser(String userId);

    // c端查询云仓
    public Paginable<Ware> queryWareCFrontPage(int start, int limit,
            Ware condition);

    // C端查详情
    public Ware getWareByCustomer(String code);

    // 检查余额红线
    public XN627805Res checkAmount(String userId);

    // 调整云仓数量
    public void editWare();

}
