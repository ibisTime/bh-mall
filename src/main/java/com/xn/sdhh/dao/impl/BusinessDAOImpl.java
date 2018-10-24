package com.xn.sdhh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xn.sdhh.dao.IBusinessDAO;
import com.xn.sdhh.dao.base.support.AMybatisTemplate;
import com.xn.sdhh.domain.Business;

@Repository("businessDAOImpl")
public class BusinessDAOImpl extends AMybatisTemplate implements IBusinessDAO {

    @Override
    public int insert(Business data) {
        return super.insert(NAMESPACE.concat("insert_business"), data);
    }

    @Override
    public int delete(Business data) {
        return 0;
    }

    @Override
    public Business select(Business condition) {
        return super.select(NAMESPACE.concat("select_business"), condition,
            Business.class);
    }

    @Override
    public long selectTotalCount(Business condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_business_count"),
            condition);
    }

    @Override
    public List<Business> selectList(Business condition) {
        return super.selectList(NAMESPACE.concat("select_business"), condition,
            Business.class);
    }

    @Override
    public List<Business> selectList(Business condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_business"), start,
            count, condition, Business.class);
    }

    @Override
    public void update(Business data) {

    }

}
