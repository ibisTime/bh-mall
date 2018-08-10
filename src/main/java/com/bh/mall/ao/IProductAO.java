package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Product;
import com.bh.mall.dto.req.XN627540Req;
import com.bh.mall.dto.req.XN627541Req;
import com.bh.mall.dto.req.XN627543Req;

/**
 * 产品
 * @author: clockorange 
 * @since: Jul 31, 2018 5:50:16 PM 
 * @history:
 */
public interface IProductAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 新增产品
    String addProduct(XN627540Req req);

    // 修改产品
    void editProduct(XN627541Req req);

    // 删除产品
    void dropProduct(String code);

    // 产品上架
    void putOnProduct(XN627543Req req);

    // 产品下架
    void putdownProduct(String code, String updater);

    // 分页查询
    Paginable<Product> selectProductPage(int start, int limit,
            Product condition);

    // 列表查询
    List<Product> selectProductList(Product condition);

    // 根据等级详细查询
    Product getProduct(String code, Integer level);

    // 根据规格查询
    Product getProductBySpecs(String specsCode, String level);

    // 详细查询单个产品
    Product getProduct(String code);

}
