package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.InnerProduct;

public interface IInnerProductDAO extends IBaseDAO<InnerProduct> {
    String NAMESPACE = IInnerProductDAO.class.getName().concat(".");

    void update(InnerProduct data);

    void putOnInnerProduct(InnerProduct data);

    void putdownInnerProduct(InnerProduct data);

    void changeQuantity(InnerProduct data);
}
