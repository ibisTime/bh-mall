package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.Product;
import com.bh.mall.enums.ECartType;
import com.bh.mall.exception.BizException;

@Service
public class CartAOImpl implements ICartAO {

    @Autowired
    private ICartBO cartBO;

    @Autowired
    private IProductBO productBO;

    @Override
    public String addCart(String userId, String productCode,
            String productSpecsCode, String quantity) {
        Cart data = cartBO.getCartByProductCode(productCode, productSpecsCode);
        if (data != null) {
            data.setQuantity(
                data.getQuantity() + StringValidater.toInteger(quantity));
            cartBO.refreshCart(data);
        }
        Cart cData = new Cart();
        String code = OrderNoGenerater.generate(EGeneratePrefix.Cart.getCode());
        data.setUserId(userId);
        Product pData = productBO.getProduct(productCode);
        if (pData.getRealNumber() < StringValidater.toInteger(quantity)
                || StringValidater.toInteger(quantity) < 0) {
            throw new BizException("xn00000", "产品数量不足或小于零");
        }

        cData.setProductCode(productCode);
        cData.setProductSpecsCode(productSpecsCode);
        cData.setQuantity(StringValidater.toInteger(quantity));
        cartBO.saveCart(data);
        return code;
    }

    @Override
    public void editCart(String code, String type, String quantity) {
        Cart data = cartBO.getCart(code);
        Product pData = productBO.getProduct(data.getProductCode());
        if (pData.getRealNumber() < StringValidater.toInteger(quantity)
                || StringValidater.toInteger(quantity) < 0) {
            throw new BizException("xn00000", "产品数量不足或小于零");
        }
        if (ECartType.ADDQuantity.getCode().equals(type)) {
            data.setQuantity(
                data.getQuantity() + StringValidater.toInteger(quantity));
        } else {
            data.setQuantity(
                data.getQuantity() - StringValidater.toInteger(quantity));
        }
        cartBO.refreshCart(data);
    }

    @Override
    public void dropCart(List<Cart> list) {
        for (Cart cart : list) {
            Cart data = cartBO.getCart(cart.getCode());
            cartBO.removeCart(data);
        }
    }

    @Override
    public Paginable<Cart> queryCartPage(int start, int limit, Cart condition) {
        return cartBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Cart> queryCartList(Cart condition) {
        return cartBO.queryCartList(condition);
    }

    @Override
    public Cart getCart(String code) {
        return cartBO.getCart(code);
    }

    @Override
    public Product getCartProduct(String productCode) {
        return productBO.getProduct(productCode);
    }

}
