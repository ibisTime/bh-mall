package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IWareDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Ware;

@Repository("wareDAOImpl")
public class WareDAOImpl extends AMybatisTemplate implements IWareDAO {

    @Override
    public int insert(Ware data) {
        return super.insert(NAMESPACE.concat("insert_ware"), data);
    }

    @Override
    public int delete(Ware data) {
        return super.delete(NAMESPACE.concat("delete_ware"), data);
    }

    @Override
    public Ware select(Ware condition) {
        return super.select(NAMESPACE.concat("select_ware"), condition,
            Ware.class);
    }

    @Override
    public long selectTotalCount(Ware condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_ware_count"),
            condition);
    }

    @Override
    public List<Ware> selectList(Ware condition) {
        return super.selectList(NAMESPACE.concat("select_ware"), condition,
            Ware.class);
    }

    @Override
    public List<Ware> selectList(Ware condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_ware"), start, count,
            condition, Ware.class);
    }

    @Override
    public void updateQuantity(Ware data) {
        super.update(NAMESPACE.concat("update_quantity"), data);
    }

    @Override
    public void updateLogCode(Ware data) {
        super.update(NAMESPACE.concat("update_logCode"), data);
    }

    @Override
    public long selectTotalCountProduct(Ware condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_product_count"),
            condition);
    }

    @Override
    public void changePrice(Ware data) {
        super.update(NAMESPACE.concat("update_price"), data);
    }

}
