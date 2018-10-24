package com.xn.sdhh.ao;

import java.util.List;

import com.xn.sdhh.bo.base.Paginable;
import com.xn.sdhh.domain.Business;
import com.xn.sdhh.dto.req.XN627130Req;

public interface IBusinessAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 开始录入
    public String addBusiness(XN627130Req req);

    // 出纳
    public void editCashier();

    // 会计
    public void editAccountant();

    // 贷后
    public void editLoan();

    // 分页查询业务
    public Paginable<Business> queryBusinessPage(Business condition, int start,
            int limit);

    // 列表查询业务
    public List<Business> queryBusinessList(Business condition);

    // 详情查询业务
    public Business getBusiness(String code);
}
