package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.Product;

@Component
public interface ICartAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    void dropCart(List<Cart> list);

    public Paginable<Cart> queryCartPage(int start, int limit, Cart condition);

    List<Cart> queryCartList(Cart condition);

    Cart getCart(String code);

    String addCart(String userId, String productCode, String productSpecsCode,
            String quantity);

    void editCart(String code, String type, String quantity);

    Product getCartProduct(String productCode);

}
