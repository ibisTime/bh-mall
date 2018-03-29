package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Cart;

public interface ICartBO extends IPaginableBO<Cart> {

    public void saveCart(Cart data);

    public void removeCart(Cart data);

    public void refreshCart(Cart data);

    public List<Cart> queryCartList(Cart condition);

    public Cart getCart(String code);

    public Cart getCartByProductCode(String productCode,
            String productSpecsCode);

}
