package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IJsAwardDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.JsAward;

@Repository("jsAwardDAOImpl")
public class JsAwardDAOImpl extends AMybatisTemplate implements IJsAwardDAO {

    @Override
    public int insert(JsAward data) {
        return super.insert(NAMESPACE.concat("insert_jsAward"), data);
    }

    @Override
    public int delete(JsAward data) {
        return super.delete(NAMESPACE.concat("delete_jsAward"), data);
    }

    @Override
    public JsAward select(JsAward condition) {
        return super.select(NAMESPACE.concat("select_jsAward"), condition,
            JsAward.class);
    }

    @Override
    public long selectTotalCount(JsAward condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_jsAward_count"),
            condition);
    }

    @Override
    public List<JsAward> selectList(JsAward condition) {
        return super.selectList(NAMESPACE.concat("select_jsAward"), condition,
            JsAward.class);
    }

    @Override
    public List<JsAward> selectList(JsAward condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_jsAward"), start,
            count, condition, JsAward.class);
    }

    @Override
    public int update(JsAward data) {
        return super.update(NAMESPACE.concat("update_jsAward"), data);
    }

}
