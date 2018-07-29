package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IWareLogDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.WareLog;

@Repository("wareLogDAOImpl")
public class WareLogDAOImpl extends AMybatisTemplate implements IWareLogDAO {

    @Override
    public int insert(WareLog data) {
        return super.insert(NAMESPACE.concat("insert_wareLog"), data);
    }

    @Override
    public int delete(WareLog data) {
        return super.delete(NAMESPACE.concat("delete_wareLog"), data);
    }

    @Override
    public WareLog select(WareLog condition) {
        return super.select(NAMESPACE.concat("select_wareLog"), condition,
            WareLog.class);
    }

    @Override
    public long selectTotalCount(WareLog condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_wareLog_count"),
            condition);
    }

    @Override
    public List<WareLog> selectList(WareLog condition) {
        return super.selectList(NAMESPACE.concat("select_wareLog"), condition,
            WareLog.class);
    }

    @Override
    public List<WareLog> selectList(WareLog condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_wareLog"), start,
            count, condition, WareLog.class);
    }

}
