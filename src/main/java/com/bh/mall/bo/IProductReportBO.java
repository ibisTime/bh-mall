package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.ProductReport;

public interface IProductReportBO extends IPaginableBO<ProductReport> {

    public void saveProductReport(InOrder data, Integer quantity,
            String realName);

    public List<ProductReport> queryProductReportList(ProductReport condition);

    public ProductReport getProductReport(String teamName, String specsCOde);

    public void refreshProductReport(ProductReport productReport,
            Integer quantity);

    public List<ProductReport> getReportByTeamName(String teamName);

}
