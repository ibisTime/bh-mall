package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Charge;

public interface IChargeDAO extends IBaseDAO<Charge> {
    String NAMESPACE = IChargeDAO.class.getName().concat(".");

    void payOrder(Charge data);
}
