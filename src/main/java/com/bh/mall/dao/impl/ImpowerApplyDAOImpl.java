
package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IImpowerApplyDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.ImpowerApply;

@Repository("impowerApplyDAOImpl")
public class ImpowerApplyDAOImpl extends AMybatisTemplate
        implements IImpowerApplyDAO {

    // 新增日志
    @Override
    public int insert(ImpowerApply data) {
        return super.insert(NAMESPACE.concat("insert_impowerapply"), data);
    }

    @Override
    public int delete(ImpowerApply data) {
        return super.delete(NAMESPACE.concat("delete_impowerapply"), data);
    }

    @Override
    public ImpowerApply select(ImpowerApply condition) {
        return super.select(NAMESPACE.concat("select_impowerapply"), condition,
            ImpowerApply.class);
    }

    @Override
    public long selectTotalCount(ImpowerApply condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_impowerapply_count"), condition);
    }

    @Override
    public List<ImpowerApply> selectList(ImpowerApply condition) {
        return super.selectList(NAMESPACE.concat("select_impowerapply"),
            condition, ImpowerApply.class);
    }

    @Override
    public List<ImpowerApply> selectList(ImpowerApply condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_impowerapply"), start,
            count, condition, ImpowerApply.class);
    }

}
