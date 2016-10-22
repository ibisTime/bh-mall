package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.CNavigate;

public interface ICNavigateAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCNavigate(CNavigate data);

    public int dropCNavigate(String code);

    public int editCNavigate(CNavigate data);

    // 导航上下架
    public int editCNavigateStatus(String code);

    public void editCNavigateCSW(CNavigate data);

    public Paginable<CNavigate> queryCNavigatePage(int start, int limit,
            CNavigate condition);

    public Paginable<CNavigate> queryCNavigatePageCSW(int start, int limit,
            CNavigate condition);

    public List<CNavigate> queryCNavigateList(CNavigate condition);

    public List<CNavigate> queryCNavigateListCSW(CNavigate condition);

    public CNavigate getCNavigate(String code);
}
