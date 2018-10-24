package com.xn.sdhh.bo;

import java.util.List;

import com.xn.sdhh.bo.base.IPaginableBO;
import com.xn.sdhh.domain.Business;

public interface IBusinessBO extends IPaginableBO<Business> {

    // 开始录入
    public String saveBusiness();

    // 出纳
    public void refreshCashier(Business data);

    // 会计
    public void refreshAccountant(Business data);

    // 贷后
    public void refreshLoan(Business data);

    // 列表查询业务
    public List<Business> queryBusinessList(Business condition);

    // 详情查询业务
    public Business getBusiness(String code);
}
