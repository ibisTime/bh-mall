package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IYxFormDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.YxForm;

@Repository("yxFormDAOImpl")
public class YxFormDAOImpl extends AMybatisTemplate implements IYxFormDAO {

    @Override
    public int insert(YxForm data) {
        return super.insert(NAMESPACE.concat("insert_yxForm"), data);
    }

    @Override
    public int delete(YxForm data) {
        return super.delete(NAMESPACE.concat("delete_yxForm"), data);
    }

    @Override
    public YxForm select(YxForm condition) {
        return super.select(NAMESPACE.concat("select_yxForm"), condition,
            YxForm.class);
    }

    @Override
    public long selectTotalCount(YxForm condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_yxForm_count"),
            condition);
    }

    @Override
    public List<YxForm> selectList(YxForm condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_yxForm"), start, limit,
            condition, YxForm.class);
    }

    @Override
    public List<YxForm> selectList(YxForm condition) {
        return super.selectList(NAMESPACE.concat("select_yxForm"), condition,
            YxForm.class);
    }

    @Override
    public void updateStatus(YxForm data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public void allotYxForm(YxForm data) {
        super.update(NAMESPACE.concat("allot_yxForm"), data);
    }

    @Override
    public void update(YxForm data) {
        super.update(NAMESPACE.concat("update_yxForm"), data);
    }

}
