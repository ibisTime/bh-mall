package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.WareHouse;

public interface IWareHouseDAO extends IBaseDAO<WareHouse> {
    String NAMESPACE = IWareHouseDAO.class.getName().concat(".");

    void updateQuantity(WareHouse data);

    void updateLogCode(WareHouse data);

    long selectTotalCountProduct(WareHouse condition);

    List<WareHouse> selectPorductList(WareHouse condition);

    void changePrice(WareHouse data);

}
