package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IProCodeDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ProCode;

@Repository("proCodeDAOImpl")
public class ProCodeDAOImpl extends AMybatisTemplate implements IProCodeDAO {

    @Override
    public int insert(ProCode data) {
        return super.insert(NAMESPACE.concat("insert_proCode"), data);
    }

    @Override
    public int delete(ProCode data) {
        return super.delete(NAMESPACE.concat("delete_proCode"), data);
    }

    @Override
    public ProCode select(ProCode condition) {
        return super.select(NAMESPACE.concat("select_proCode"), condition,
            ProCode.class);
    }

    @Override
    public long selectTotalCount(ProCode condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_proCode_count"),
            condition);
    }

    @Override
    public List<ProCode> selectList(ProCode condition) {
        return super.selectList(NAMESPACE.concat("select_proCode"), condition,
            ProCode.class);
    }

    @Override
    public List<ProCode> selectList(ProCode condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_proCode"), start,
            count, condition, ProCode.class);
    }

    @Override
    public void update(ProCode data) {
        super.update(NAMESPACE.concat("update_proCode"), data);
    }

    @Override
    public List<ProCode> selectCodeList(ProCode condition) {
        return super.selectList(NAMESPACE.concat("select_code"), condition,
            ProCode.class);
    }

    @Override
    public void splitSingle(ProCode data) {
        super.update(NAMESPACE.concat("split_single"), data);
    }

}
