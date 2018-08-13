package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IProductReportDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ProductReport;

@Repository("productReportDAOImpl")
public class ProductReportDAOImpl extends AMybatisTemplate
        implements IProductReportDAO {

    @Override
    public int insert(ProductReport data) {
        return super.insert(NAMESPACE.concat("insert_productReport"), data);
    }

    @Override
    public int delete(ProductReport data) {
        return super.delete(NAMESPACE.concat("delete_productReport"), data);
    }

    @Override
    public ProductReport select(ProductReport condition) {
        return super.select(NAMESPACE.concat("select_productReport"), condition,
            ProductReport.class);
    }

    @Override
    public long selectTotalCount(ProductReport condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_productReport_count"), condition);
    }

    @Override
    public List<ProductReport> selectList(ProductReport condition) {
        return super.selectList(NAMESPACE.concat("select_productReport"),
            condition, ProductReport.class);
    }

    @Override
    public List<ProductReport> selectList(ProductReport condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_productReport"), start,
            count, condition, ProductReport.class);
    }

    @Override
    public void update(ProductReport data) {
        super.update(NAMESPACE.concat("update_productReport"), data);
    }

    @Override
    public List<ProductReport> selectByTeamName(ProductReport condition) {
        return super.selectList(NAMESPACE.concat("select_byTeamName"),
            condition, ProductReport.class);
    }

}
