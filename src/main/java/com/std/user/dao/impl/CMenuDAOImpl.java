package com.std.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.user.dao.ICMenuDAO;
import com.std.user.dao.base.support.AMybatisTemplate;
import com.std.user.domain.CMenu;

@Repository("cMenuDAOImpl")
public class CMenuDAOImpl extends AMybatisTemplate implements ICMenuDAO {

    @Override
    public int insert(CMenu data) {
        return super.insert(NAMESPACE.concat("insert_cMenu"), data);
    }

    @Override
    public int delete(CMenu data) {
        return super.delete(NAMESPACE.concat("delete_cMenu"), data);
    }

    @Override
    public CMenu select(CMenu condition) {
        return super.select(NAMESPACE.concat("select_cMenu"), condition,
            CMenu.class);
    }

    @Override
    public long selectTotalCount(CMenu condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cMenu_count"),
            condition);
    }

    @Override
    public List<CMenu> selectList(CMenu condition) {
        return super.selectList(NAMESPACE.concat("select_cMenu"), condition,
            CMenu.class);
    }

    @Override
    public List<CMenu> selectList(CMenu condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cMenu"), start, count,
            condition, CMenu.class);
    }

    @Override
    public int update(CMenu data) {
        return super.update(NAMESPACE.concat("update_cMenu"), data);
    }

    @Override
    public int updateStatus(CMenu data) {
        return super.update(NAMESPACE.concat("update_cMenu_status"), data);
    }
}
