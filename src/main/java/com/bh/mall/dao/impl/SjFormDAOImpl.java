package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISjFormDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.SjForm;

@Repository("sjFormDAOImpl")
public class SjFormDAOImpl extends AMybatisTemplate implements ISjFormDAO {

    @Override
    public void approveUpgrade(SjForm data) {
        super.update(NAMESPACE.concat("approve_upgrade"), data);
    }

    @Override
    public void cancelUplevel(SjForm data) {
        super.update(NAMESPACE.concat("cancel_uplevel"), data);
    }

    @Override
    public void upgradeLevel(SjForm data) {
        super.update(NAMESPACE.concat("upgrade_level"), data);
    }

    @Override
    public void updateHighUser(SjForm data) {
        super.update(NAMESPACE.concat("update_highUser"), data);
    }

    @Override
    public int insert(SjForm data) {
        return super.insert(NAMESPACE.concat("insert_upLevelApply"), data);
    }

    @Override
    public int delete(SjForm data) {
        return super.delete(NAMESPACE.concat("delete_upLevelApply"), data);
    }

    @Override
    public SjForm select(SjForm condition) {
        return super.select(NAMESPACE.concat("select_upLevelApply"), condition,
            SjForm.class);
    }

    @Override
    public long selectTotalCount(SjForm condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_upLevelApply_count"), condition);
    }

    @Override
    public List<SjForm> selectList(SjForm condition) {
        return super.selectList(NAMESPACE.concat("select_upLevelApply"),
            condition, SjForm.class);
    }

    @Override
    public List<SjForm> selectList(SjForm condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_upLevelApply"), start,
            count, condition, SjForm.class);
    }

}
