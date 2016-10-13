package com.std.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.user.dao.ICBannerDAO;
import com.std.user.dao.base.support.AMybatisTemplate;
import com.std.user.domain.CBanner;

@Repository("cBannerDAOImpl")
public class CBannerDAOImpl extends AMybatisTemplate implements ICBannerDAO {

    @Override
    public int insert(CBanner data) {
        return super.insert(NAMESPACE.concat("insert_cBanner"), data);
    }

    @Override
    public int delete(CBanner data) {
        return super.delete(NAMESPACE.concat("delete_cBanner"), data);
    }

    @Override
    public CBanner select(CBanner condition) {
        return super.select(NAMESPACE.concat("select_cBanner"), condition,
            CBanner.class);
    }

    @Override
    public long selectTotalCount(CBanner condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cBanner_count"),
            condition);
    }

    @Override
    public List<CBanner> selectList(CBanner condition) {
        return super.selectList(NAMESPACE.concat("select_cBanner"), condition,
            CBanner.class);
    }

    @Override
    public List<CBanner> selectList(CBanner condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cBanner"), start,
            count, condition, CBanner.class);
    }

    @Override
    public int update(CBanner data) {
        return super.update(NAMESPACE.concat("update_cBanner"), data);
    }
}
