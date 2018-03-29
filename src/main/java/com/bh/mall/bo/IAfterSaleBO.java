package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AfterSale;

public interface IAfterSaleBO extends IPaginableBO<AfterSale> {

    public void saveAfterSale(AfterSale data);

    public void removeAfterSale(String code);

    public List<AfterSale> queryAfterSaleList(AfterSale condition);

    public AfterSale getAfterSale(String code);

    public void approvrAfterSale(AfterSale data);

    public void changeProduct(AfterSale data);

}
