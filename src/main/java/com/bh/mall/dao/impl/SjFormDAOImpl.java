package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISjFormDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.SjForm;

@Repository("sjFormDAOImpl")
public class SjFormDAOImpl extends AMybatisTemplate implements ISjFormDAO {

    @Override
    public void approveSjForm(SjForm data) {
        super.update(NAMESPACE.concat("approve_sjForm"), data);
    }

    @Override
    public void cancelSjForm(SjForm data) {
        super.update(NAMESPACE.concat("insert_sjForm"), data);
    }

    @Override
    public void applySjForm(SjForm data) {
        super.update(NAMESPACE.concat("insert_sjForm"), data);
    }

    @Override
    public void update(SjForm data) {
        super.update(NAMESPACE.concat("update_sjForm"), data);
    }

    @Override
    public int insert(SjForm data) {
        return super.insert(NAMESPACE.concat("insert_sjForm"), data);
    }

    @Override
    public int delete(SjForm data) {
        return super.delete(NAMESPACE.concat("delete_sjForm"), data);
    }

    @Override
    public SjForm select(SjForm condition) {
        return super.select(NAMESPACE.concat("select_sjForm"), condition,
            SjForm.class);
    }

    @Override
    public long selectTotalCount(SjForm condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_sjForm_count"),
            condition);
    }

    @Override
    public List<SjForm> selectList(SjForm condition) {
        return super.selectList(NAMESPACE.concat("select_sjForm"), condition,
            SjForm.class);
    }

    @Override
    public List<SjForm> selectList(SjForm condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sjForm"), start, count,
            condition, SjForm.class);
    }

}
