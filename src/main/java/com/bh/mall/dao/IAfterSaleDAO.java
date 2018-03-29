package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AfterSale;

//dao层 
public interface IAfterSaleDAO extends IBaseDAO<AfterSale> {
    String NAMESPACE = IAfterSaleDAO.class.getName().concat(".");

    void approvreAfterSale(AfterSale data);

    void changeProduct(AfterSale data);

}
