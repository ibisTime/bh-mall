package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.WareHouseSpecs;

//dao层 
public interface IWareHouseSpecsDAO extends IBaseDAO<WareHouseSpecs> {
    String NAMESPACE = IWareHouseSpecsDAO.class.getName().concat(".");

    void update(WareHouseSpecs data);

    void updateQuantity(WareHouseSpecs data);
}
