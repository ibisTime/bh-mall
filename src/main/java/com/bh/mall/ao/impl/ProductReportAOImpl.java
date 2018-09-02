package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IProductReportAO;
import com.bh.mall.bo.IProductReportBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ProductReport;

@Service
public class ProductReportAOImpl implements IProductReportAO {

    @Autowired
    private IProductReportBO productReportBO;

    @Override
    public Paginable<ProductReport> queryProductReportPage(int start, int limit,
            ProductReport condition) {
        Paginable<ProductReport> page = productReportBO.getPaginable(start,
            limit, condition);

        for (ProductReport data : page.getList()) {
            ProductReport prCondition = new ProductReport();
            prCondition.setTeamName(data.getTeamName());
            List<ProductReport> list = productReportBO
                .queryProductReportList(prCondition);
            data.setList(list);
        }

        return page;
    }

    @Override
    public List<ProductReport> queryProductReportList(ProductReport condition) {

        return productReportBO.queryProductReportList(condition);
    }

    @Override
    public ProductReport getProductReport(String code) {
        return null;
    }
}
