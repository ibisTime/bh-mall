package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductLog;

@Component
public interface IProductLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void dropProductLog(String code);

    public void editProductLog(ProductLog data);

    public Paginable<ProductLog> queryProductLogPage(int start, int limit,
            ProductLog condition);

    public List<ProductLog> queryProductLogList(ProductLog condition);

    public ProductLog getProductLog(String code);

}
