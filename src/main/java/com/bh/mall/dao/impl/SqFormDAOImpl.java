
package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.ISqFormDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.SqForm;

@Repository("sqFormDAOImpl")
public class SqFormDAOImpl extends AMybatisTemplate implements ISqFormDAO {

    @Override
    public void approveImpower(SqForm data) {
        super.update(NAMESPACE.concat("approve_impower"), data);

    }

    @Override
    public void cancelImpower(SqForm data) {
        super.update(NAMESPACE.concat("cancel_impower"), data);
    }

    @Override
    public void addInfo(SqForm data) {
        super.update(NAMESPACE.concat("add_info"), data);
    }

    // 新增日志
    @Override
    public int insert(SqForm data) {
        return super.insert(NAMESPACE.concat("insert_impowerapply"), data);
    }

    @Override
    public int delete(SqForm data) {
        return super.delete(NAMESPACE.concat("delete_impowerapply"), data);
    }

    @Override
    public SqForm select(SqForm condition) {
        return super.select(NAMESPACE.concat("select_impowerapply"), condition,
            SqForm.class);
    }

    @Override
    public long selectTotalCount(SqForm condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_impowerapply_count"), condition);
    }

    @Override
    public List<SqForm> selectList(SqForm condition) {
        return super.selectList(NAMESPACE.concat("select_impowerapply"),
            condition, SqForm.class);
    }

    @Override
    public List<SqForm> selectList(SqForm condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_impowerapply"), start,
            count, condition, SqForm.class);
    }

}
