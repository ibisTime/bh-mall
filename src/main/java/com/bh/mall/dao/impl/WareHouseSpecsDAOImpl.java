package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IWareHouseSpecsDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.WareHouseSpecs;

@Repository("wareHouseSpecsDAOImpl")
public class WareHouseSpecsDAOImpl extends AMybatisTemplate
        implements IWareHouseSpecsDAO {

    @Override
    public int insert(WareHouseSpecs data) {
        return super.insert(NAMESPACE.concat("insert_wareHouseSpecs"), data);
    }

    @Override
    public int delete(WareHouseSpecs data) {
        return super.delete(NAMESPACE.concat("delete_wareHouseSpecs"), data);
    }

    @Override
    public WareHouseSpecs select(WareHouseSpecs condition) {
        return super.select(NAMESPACE.concat("select_wareHouseSpecs"),
            condition, WareHouseSpecs.class);
    }

    @Override
    public long selectTotalCount(WareHouseSpecs condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_wareHouseSpecs_count"), condition);
    }

    @Override
    public List<WareHouseSpecs> selectList(WareHouseSpecs condition) {
        return super.selectList(NAMESPACE.concat("select_wareHouseSpecs"),
            condition, WareHouseSpecs.class);
    }

    @Override
    public List<WareHouseSpecs> selectList(WareHouseSpecs condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wareHouseSpecs"),
            start, count, condition, WareHouseSpecs.class);
    }

    @Override
    public void update(WareHouseSpecs data) {
        // TODO Auto-generated method stub

    }

}
