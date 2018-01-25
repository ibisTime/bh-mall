package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.CNavigate;

public interface ICNavigateBO extends IPaginableBO<CNavigate> {

    public boolean isCNavigateExist(String code);

    public String saveCNavigate(CNavigate data);

    public int removeCNavigate(String code);

    public int refreshCNavigate(CNavigate data);

    public List<CNavigate> queryCNavigateList(CNavigate condition);

    public CNavigate getCNavigate(String code);

}
