package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IMiniCodeDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.MiniCode;

@Repository("miniCodeDAOImpl")
public class MiniCodeDAOImpl extends AMybatisTemplate implements IMiniCodeDAO {

    @Override
    public int insert(MiniCode data) {
        return super.insert(NAMESPACE.concat("insert_miniCode"), data);
    }

    @Override
    public int delete(MiniCode data) {
        return super.delete(NAMESPACE.concat("delete_miniCode"), data);
    }

    @Override
    public MiniCode select(MiniCode condition) {
        return super.select(NAMESPACE.concat("select_miniCode"), condition,
            MiniCode.class);
    }

    @Override
    public long selectTotalCount(MiniCode condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_miniCode_count"),
            condition);
    }

    @Override
    public List<MiniCode> selectList(MiniCode condition) {
        return super.selectList(NAMESPACE.concat("select_miniCode"), condition,
            MiniCode.class);
    }

    @Override
    public List<MiniCode> selectList(MiniCode condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_miniCode"), start,
            count, condition, MiniCode.class);
    }

    @Override
    public void update(MiniCode data) {
        super.update(NAMESPACE.concat("update_miniCode"), data);
    }

    @Override
    public void updateStatus(MiniCode data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public List<MiniCode> selectCodeList(MiniCode condition) {
        return super.selectList(NAMESPACE.concat("select_code"), condition,
            MiniCode.class);
    }

    @Override
    public void updateNumber(MiniCode data) {
        super.update(NAMESPACE.concat("update_number"), data);
    }

}
