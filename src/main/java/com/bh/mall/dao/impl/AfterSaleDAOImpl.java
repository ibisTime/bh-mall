package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAfterSaleDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AfterSale;

@Repository("afterSaleDAOImpl")
public class AfterSaleDAOImpl extends AMybatisTemplate
        implements IAfterSaleDAO {

    @Override
    public int insert(AfterSale data) {
        return super.insert(NAMESPACE.concat("insert_afterSale"), data);
    }

    @Override
    public int delete(AfterSale data) {
        return super.delete(NAMESPACE.concat("delete_afterSale"), data);
    }

    @Override
    public AfterSale select(AfterSale condition) {
        return super.select(NAMESPACE.concat("select_afterSale"), condition,
            AfterSale.class);
    }

    @Override
    public long selectTotalCount(AfterSale condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_afterSale_count"), condition);
    }

    @Override
    public List<AfterSale> selectList(AfterSale condition) {
        return super.selectList(NAMESPACE.concat("select_afterSale"), condition,
            AfterSale.class);
    }

    @Override
    public List<AfterSale> selectList(AfterSale condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_afterSale"), start,
            count, condition, AfterSale.class);
    }

    @Override
    public void approvreAfterSale(AfterSale data) {
        super.update(NAMESPACE.concat("approvre_afterSale"), data);
    }

    @Override
    public void changeProduct(AfterSale data) {
        super.update(NAMESPACE.concat("change_product"), data);
    }

}
