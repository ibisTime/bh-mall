
package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISqFormDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.SqForm;

@Repository("sqFormDAOImpl")
public class SqFormDAOImpl extends AMybatisTemplate implements ISqFormDAO {

    @Override
    public void approveSqForm(SqForm data) {
        super.update(NAMESPACE.concat("approve_sqForm"), data);

    }

    @Override
    public void cancelSqForm(SqForm data) {
        super.update(NAMESPACE.concat("cancel_sqForm"), data);
    }

    @Override
    public void addInfo(SqForm data) {
        super.update(NAMESPACE.concat("add_info"), data);
    }

    @Override
    public int insert(SqForm data) {
        return super.insert(NAMESPACE.concat("insert_sqForm"), data);
    }

    @Override
    public int delete(SqForm data) {
        return super.delete(NAMESPACE.concat("delete_sqForm"), data);
    }

    @Override
    public SqForm select(SqForm condition) {
        return super.select(NAMESPACE.concat("select_sqForm"), condition,
            SqForm.class);
    }

    @Override
    public long selectTotalCount(SqForm condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_sqForm_count"),
            condition);
    }

    @Override
    public List<SqForm> selectList(SqForm condition) {
        return super.selectList(NAMESPACE.concat("select_sqForm"), condition,
            SqForm.class);
    }

    @Override
    public List<SqForm> selectList(SqForm condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sqForm"), start, count,
            condition, SqForm.class);
    }

    @Override
    public void applySqForm(SqForm data) {
        super.insert(NAMESPACE.concat("insert_sqForm"), data);
    }

    @Override
    public void update(SqForm data) {
        super.update(NAMESPACE.concat("udpate_sqForm"), data);
    }

}
