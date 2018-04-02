package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.enums.ECartType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.exception.BizException;

@Service
public class CartAOImpl implements ICartAO {

    @Autowired
    private ICartBO cartBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public String addCart(String userId, String productCode,
            String productSpecsCode, String quantity) {

        Product pData = productBO.getProduct(productCode);
        if (!EProductStatus.Shelf_YES.getCode().equals(pData.getStatus())) {
            throw new BizException("xn00000", "产品未上架，无法添加购物车");
        }
        if (pData.getRealNumber() < StringValidater.toInteger(quantity)
                || StringValidater.toInteger(quantity) < 0) {
            throw new BizException("xn00000", "产品数量不足或小于零");
        }

        Cart data = cartBO.getCartByProductCode(productCode, productSpecsCode);

        String code = OrderNoGenerater.generate(EGeneratePrefix.Cart.getCode());
        if (data != null) {
            code = data.getCode();
            data.setQuantity(data.getQuantity()
                    + StringValidater.toInteger(quantity));
            cartBO.refreshCart(data);
        } else {
            data = new Cart();
            data.setCode(code);
            data.setUserId(userId);

            data.setProductCode(productCode);
            data.setProductSpecsCode(productSpecsCode);
            data.setQuantity(StringValidater.toInteger(quantity));
            cartBO.saveCart(data);
        }

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
            data.setQuantity(data.getQuantity()
                    + StringValidater.toInteger(quantity));
        } else {
            data.setQuantity(data.getQuantity()
                    - StringValidater.toInteger(quantity));
        }
        cartBO.refreshCart(data);
    }

    @Override
    public void dropCart(List<String> list) {
        for (String code : list) {
            Cart data = cartBO.getCart(code);
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
    public Product getCartProduct(String productSpecsCode) {
        ProductSpecs psData = productSpecsBO.getProductSpecs(productSpecsCode);
        List<ProductSpecs> list = new ArrayList<ProductSpecs>();
        list.add(psData);
        Product data = productBO.getProduct(psData.getProductCode());
        data.setSpecsList(list);
        return data;
    }

}
