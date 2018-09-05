package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Ware;

public interface IWareDAO extends IBaseDAO<Ware> {
    String NAMESPACE = IWareDAO.class.getName().concat(".");

    void updateQuantity(Ware data);

    void updateLogCode(Ware data);

    long selectTotalCountProduct(Ware condition);

    void changePrice(Ware data);

    List<Ware> selectByProduct(Ware condition);

}
