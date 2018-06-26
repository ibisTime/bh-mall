package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Product;
import com.bh.mall.dto.req.XN627540Req;
import com.bh.mall.dto.req.XN627541Req;
import com.bh.mall.dto.req.XN627543Req;
import com.bh.mall.dto.req.XN627545Req;

public interface IProductAO {

    String DEFAULT_ORDER_COLUMN = "code";

    String addProduct(XN627540Req req);

    void editProduct(XN627541Req req);

    Product getProduct(String code);

    void dropProduct(String code);

    void putdownProduct(String code, String updater);

    Paginable<Product> selectProductPage(int start, int limit,
            Product condition);

    List<Product> selectProductList(Product condition);

    void editRepertory(XN627545Req req);

    void putOnProduct(XN627543Req req);

    Paginable<Product> selectProductByFrontPage(int start, int limit,
            Product condition);

    Product getProduct(String code, Integer integer);

}
