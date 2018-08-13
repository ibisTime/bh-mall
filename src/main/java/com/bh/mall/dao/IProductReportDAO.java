package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ProductReport;

//dao层 
public interface IProductReportDAO extends IBaseDAO<ProductReport> {
    String NAMESPACE = IProductReportDAO.class.getName().concat(".");

    void update(ProductReport data);

    List<ProductReport> selectByTeamName(ProductReport condition);
}
