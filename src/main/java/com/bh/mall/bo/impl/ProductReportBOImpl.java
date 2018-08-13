package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IProductReportBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IProductReportDAO;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.ProductReport;

@Component
public class ProductReportBOImpl extends PaginableBOImpl<ProductReport>
        implements IProductReportBO {

    @Autowired
    private IProductReportDAO productReportDAO;

    public void saveOrderReport(ProductReport data) {
    }

    @Override
    public List<ProductReport> queryProductReportList(ProductReport condition) {
        return productReportDAO.selectList(condition);
    }

    @Override
    public ProductReport getProductReport(String teamName, String specsCode) {
        ProductReport data = null;
        if (StringUtils.isNotBlank(teamName)
                && StringUtils.isNotBlank(specsCode)) {
            ProductReport condition = new ProductReport();
            condition.setTeamName(teamName);
            condition.setSpecsCode(specsCode);
            data = productReportDAO.select(condition);
        }
        return data;
    }

    @Override
    public void saveProductReport(InOrder order) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProductReport.getCode());

        ProductReport data = new ProductReport();
        data.setCode(code);
        data.setTeamName(order.getTeamName());
        data.setProductName(order.getProductName());
        data.setSpecsName(order.getSpecsName());

        data.setSpecsCode(order.getSpecsCode());
        data.setQuantity(order.getQuantity());
        productReportDAO.insert(data);

    }

    @Override
    public void refreshProductReport(ProductReport data, Integer quantity) {
        data.setQuantity(quantity);
        productReportDAO.update(data);
    }

    @Override
    public List<ProductReport> getReportByTeamName(String teamName) {
        ProductReport condition = new ProductReport();
        condition.setTeamName(teamName);
        return productReportDAO.selectByTeamName(condition);
    }
}
