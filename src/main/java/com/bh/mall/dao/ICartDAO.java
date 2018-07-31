package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Cart;

public interface ICartDAO extends IBaseDAO<Cart> {
    String NAMESPACE = ICartDAO.class.getName().concat(".");

    /**
     * 更新购物车
     * @param data 
     * @create: 2018年7月31日 下午2:24:10 LENOVO
     * @history:
     */
    void update(Cart data);
}
