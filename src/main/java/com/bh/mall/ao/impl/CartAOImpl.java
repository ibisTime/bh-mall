package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.bo.ICartBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Cart;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.exception.BizException;

@Service
public class CartAOImpl implements ICartAO {

    @Autowired
    ICartBO cartBO;

    @Autowired
    IProductBO productBO;

    @Autowired
    IProductSpecsBO productSpecsBO;

    @Autowired
    IProductSpecsPriceBO productSpecsPriceBO;

    @Override
    public String addCart(String userId, String productCode,
            String productSpecsCode, String quantity) {

        if (StringValidater.toInteger(quantity) <= 0) {
            throw new BizException("xn00000", "添加数量不能少于零");
        }

        productBO.getProduct(productCode);
        Cart data = cartBO.getCartByProductCode(productCode, productSpecsCode);
        ProductSpecsPrice specsPrice = productSpecsPriceBO
            .getPriceBySpecsCode(productSpecsCode, 6);

        String code = OrderNoGenerater.generate(EGeneratePrefix.Cart.getCode());
        if (data != null) {
            code = data.getCode();
            data.setQuantity(
                data.getQuantity() + StringValidater.toInteger(quantity));
            cartBO.refreshCart(data);
        } else {
            data = new Cart();
            data.setCode(code);
            data.setUserId(userId);

            data.setProductCode(productCode);
            data.setProductSpecsCode(productSpecsCode);
            data.setQuantity(StringValidater.toInteger(quantity));
            data.setPrice(specsPrice.getPrice());
            cartBO.saveCart(data);
        }

        return code;
    }

    @Override
    public void editCart(String code, String quantity) {
        Cart data = cartBO.getCart(code);
        // Product pData = productBO.getProduct(data.getProductCode());
        // if (pData.getRealNumber() < StringValidater.toInteger(quantity)
        // || StringValidater.toInteger(quantity) < 0) {
        // throw new BizException("xn00000", "产品数量不足或小于零");
        // }
        data.setQuantity(StringValidater.toInteger(quantity));
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
        Paginable<Cart> page = cartBO.getPaginable(start, limit, condition);
        for (Cart data : page.getList()) {
            Product product = productBO.getProduct(data.getProductCode());
            data.setProduct(product);
            ProductSpecs specs = productSpecsBO
                .getProductSpecs(data.getProductSpecsCode());
            data.setSpecsName(specs.getName());
        }
        return page;
    }

    @Override
    public List<Cart> queryCartList(Cart condition) {
        return cartBO.queryCartList(condition);
    }

    @Override
    public Cart getCart(String code) {
        Cart data = cartBO.getCart(code);
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        ProductSpecs specs = productSpecsBO
            .getProductSpecs(data.getProductSpecsCode());
        data.setSpecsName(specs.getName());
        return data;
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
