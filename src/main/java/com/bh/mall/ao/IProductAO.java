package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Product;
import com.bh.mall.dto.req.XN627540Req;
import com.bh.mall.dto.req.XN627541Req;
import com.bh.mall.dto.req.XN627545Req;

public interface IProductAO {
    String DEFAULT_ORDER_COLUMN = "code";

    String addProduct(XN627540Req req);

    void editProduct(XN627541Req req);

    Product getProduct(String code);

    void dropProduct(String code);

    void putonProduct(String code, String orderNo, String isFree,
            String updater);

    void putdownProduct(String code, String updater);

    Paginable<Product> selectProductPage(int start, int limit,
            Product condition);

    List<Product> selectProductList(Product condition);

    void editRepertory(XN627545Req req);

    Paginable<Product> selectProductPageByFront(int start, int limit,
            Product condition);
}
