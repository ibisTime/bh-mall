package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Cart;

public interface ICartDAO extends IBaseDAO<Cart> {
    String NAMESPACE = ICartDAO.class.getName().concat(".");

    void update(Cart data);
}
