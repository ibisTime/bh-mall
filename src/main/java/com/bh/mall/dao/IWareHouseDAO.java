package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.WareHouse;

//dao层 
public interface IWareHouseDAO extends IBaseDAO<WareHouse> {
    String NAMESPACE = IWareHouseDAO.class.getName().concat(".");

    void updateQuantity(WareHouse data);
}
