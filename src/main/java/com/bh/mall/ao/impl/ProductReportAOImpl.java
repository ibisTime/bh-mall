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
            ProductReport pCondition = new ProductReport();
            pCondition.setTeamName(data.getTeamName());
            pCondition.setProductCode(data.getProductCode());
            List<ProductReport> list = productReportBO
                .queryProductReportList(pCondition);
            for (ProductReport productReport : list) {
                data.setProductQuantity(productReport.getProductName() + ":"
                        + "[" + productReport.getQuantity() + "]"
                        + productReport.getSpecsName());
            }

        }
        return page;
    }

    @Override
    public List<ProductReport> queryProductReportList(ProductReport condition) {
        List<ProductReport> list = productReportBO
            .queryProductReportList(condition);
        for (ProductReport data : list) {
            ProductReport pCondition = new ProductReport();
            pCondition.setTeamName(data.getTeamName());
            pCondition.setProductCode(data.getProductCode());
            List<ProductReport> pList = productReportBO
                .queryProductReportList(pCondition);
            for (ProductReport productReport : pList) {
                data.setProductQuantity(productReport.getProductName() + ":"
                        + "[" + productReport.getQuantity() + "]"
                        + productReport.getSpecsName());
            }

        }
        return list;
    }

    @Override
    public ProductReport getProductReport(String code) {
        return null;
    }
}
