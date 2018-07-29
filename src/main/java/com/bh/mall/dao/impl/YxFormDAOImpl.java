package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IYxFormDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.YxForm;

@Repository("yxFormDAOImpl")
public class YxFormDAOImpl extends AMybatisTemplate implements IYxFormDAO {

    @Override
    public void allotAgent(YxForm data) {
        super.update(NAMESPACE.concat("allot_gency"), data);
    }

    @Override
    public void ignore(YxForm data) {
        super.update(NAMESPACE.concat("ignore_gency"), data);

    }

    @Override
    public void applyIntent(YxForm data) {
        super.update(NAMESPACE.concat("apply_intent"), data);

    }

    @Override
    public void toApply(YxForm data) {
        super.update(NAMESPACE.concat("to_apply"), data);
    }

    @Override
    public void acceptIntention(YxForm data) {
        super.update(NAMESPACE.concat("accept_intention"), data);
    }

    @Override
    public int insert(YxForm data) {
        return super.insert(NAMESPACE.concat("insert_agentAllot"), data);
    }

    @Override
    public int delete(YxForm data) {
        return super.delete(NAMESPACE.concat("delete_agentAllot"), data);
    }

    @Override
    public YxForm select(YxForm condition) {
        return super.select(NAMESPACE.concat("select_agentAllot"), condition,
            YxForm.class);
    }

    @Override
    public long selectTotalCount(YxForm condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_agentAllot_count"), condition);
    }

    @Override
    public List<YxForm> selectList(YxForm condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_agentAllot"), start,
            limit, condition, YxForm.class);
    }

    @Override
    public List<YxForm> selectList(YxForm condition) {
        return super.selectList(NAMESPACE.concat("select_agentAllot"),
            condition, YxForm.class);
    }

}
