package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductReport;

@Component
public interface IProductReportAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<ProductReport> queryProductReportPage(int start, int limit,
            ProductReport condition);

    public List<ProductReport> queryProductReportList(ProductReport condition);

    public ProductReport getProductReport(String code);

}
