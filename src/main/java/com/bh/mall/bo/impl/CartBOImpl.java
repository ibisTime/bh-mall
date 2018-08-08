package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.ICartDAO;
import com.bh.mall.domain.Cart;
import com.bh.mall.exception.BizException;

@Component
public class CartBOImpl extends PaginableBOImpl<Cart> implements ICartBO {

    @Autowired
    private ICartDAO cartDAO;

    @Override
    public void saveCart(Cart data) {
        cartDAO.insert(data);
    }

    @Override
    public void removeCart(Cart data) {
        cartDAO.delete(data);
    }

    @Override
    public void refreshCart(Cart data) {
        cartDAO.update(data);
    }

    @Override
    public List<Cart> queryCartList(Cart condition) {
        return cartDAO.selectList(condition);
    }

    @Override
    public Cart getCart(String code) {
        Cart data = null;
        if (StringUtils.isNotBlank(code)) {
            Cart condition = new Cart();
            condition.setCode(code);
            data = cartDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "购物车记录不存在");
            }
        }
        return data;
    }

    @Override
    public Cart getCartByProductCode(String userId, String specsCode) {
        Cart condition = new Cart();
        condition.setUserId(userId);
        condition.setSpecsCode(specsCode);
        return cartDAO.select(condition);
    }
}
