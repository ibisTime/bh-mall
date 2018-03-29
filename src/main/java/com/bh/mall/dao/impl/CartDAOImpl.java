package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ICartDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Cart;

@Repository("cartDAOImpl")
public class CartDAOImpl extends AMybatisTemplate implements ICartDAO {

    @Override
    public int insert(Cart data) {
        return super.insert(NAMESPACE.concat("insert_cart"), data);
    }

    @Override
    public int delete(Cart data) {
        return super.delete(NAMESPACE.concat("delete_cart"), data);
    }

    @Override
    public Cart select(Cart condition) {
        return super.select(NAMESPACE.concat("select_cart"), condition,
            Cart.class);
    }

    @Override
    public long selectTotalCount(Cart condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cart_count"),
            condition);
    }

    @Override
    public List<Cart> selectList(Cart condition) {
        return super.selectList(NAMESPACE.concat("select_cart"), condition,
            Cart.class);
    }

    @Override
    public List<Cart> selectList(Cart condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cart"), start, count,
            condition, Cart.class);
    }

    @Override
    public void update(Cart data) {
        super.update(NAMESPACE.concat("update_cart"), data);
    }

}
