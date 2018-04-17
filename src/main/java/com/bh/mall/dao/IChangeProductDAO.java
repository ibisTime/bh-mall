package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ChangeProduct;

public interface IChangeProductDAO extends IBaseDAO<ChangeProduct> {
    String NAMESPACE = IChangeProductDAO.class.getName().concat(".");

    void updateChangePrice(ChangeProduct data);

    void approveChange(ChangeProduct data);

    List<ChangeProduct> queryChangeProductPage(int start, int pageSize,
            ChangeProduct condition);
}
