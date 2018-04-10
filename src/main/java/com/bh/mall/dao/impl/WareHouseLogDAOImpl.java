package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IWareHouseLogDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.WareHouseLog;

@Repository("wareHouseLogDAOImpl")
public class WareHouseLogDAOImpl extends AMybatisTemplate
        implements IWareHouseLogDAO {

    @Override
    public int insert(WareHouseLog data) {
        return super.insert(NAMESPACE.concat("insert_wareHouseLog"), data);
    }

    @Override
    public int delete(WareHouseLog data) {
        return super.delete(NAMESPACE.concat("delete_wareHouseLog"), data);
    }

    @Override
    public WareHouseLog select(WareHouseLog condition) {
        return super.select(NAMESPACE.concat("select_wareHouseLog"), condition,
            WareHouseLog.class);
    }

    @Override
    public long selectTotalCount(WareHouseLog condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_wareHouseLog_count"), condition);
    }

    @Override
    public List<WareHouseLog> selectList(WareHouseLog condition) {
        return super.selectList(NAMESPACE.concat("select_wareHouseLog"),
            condition, WareHouseLog.class);
    }

    @Override
    public List<WareHouseLog> selectList(WareHouseLog condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wareHouseLog"), start,
            count, condition, WareHouseLog.class);
    }

}
