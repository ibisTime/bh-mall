package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IWareHouseDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.WareHouse;

@Repository("wareHouseDAOImpl")
public class WareHouseDAOImpl extends AMybatisTemplate
        implements IWareHouseDAO {

    @Override
    public int insert(WareHouse data) {
        return super.insert(NAMESPACE.concat("insert_wareHouse"), data);
    }

    @Override
    public int delete(WareHouse data) {
        return super.delete(NAMESPACE.concat("delete_wareHouse"), data);
    }

    @Override
    public WareHouse select(WareHouse condition) {
        return super.select(NAMESPACE.concat("select_wareHouse"), condition,
            WareHouse.class);
    }

    @Override
    public long selectTotalCount(WareHouse condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_wareHouse_count"), condition);
    }

    @Override
    public List<WareHouse> selectList(WareHouse condition) {
        return super.selectList(NAMESPACE.concat("select_wareHouse"), condition,
            WareHouse.class);
    }

    @Override
    public List<WareHouse> selectList(WareHouse condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wareHouse"), start,
            count, condition, WareHouse.class);
    }

    @Override
    public void updateQuantity(WareHouse data) {
        super.update(NAMESPACE.concat("update_quantity"), data);
    }

    @Override
    public void updateLogCode(WareHouse data) {
        super.update(NAMESPACE.concat("update_logCode"), data);
    }

    @Override
    public long selectTotalCountProduct(WareHouse condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_product_count"),
            condition);
    }

    @Override
    public List<WareHouse> selectPorductList(WareHouse condition) {
        return super.selectList(NAMESPACE.concat("select_wareHouse_product"),
            condition, WareHouse.class);
    }

    @Override
    public void changePrice(WareHouse data) {
        super.update(NAMESPACE.concat("update_Price"), data);
    }

}
