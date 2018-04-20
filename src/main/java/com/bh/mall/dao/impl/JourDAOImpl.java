package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IJourDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Jour;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 上午11:26:51 
 * @history:
 */
@Repository("jourDAOImpl")
public class JourDAOImpl extends AMybatisTemplate implements IJourDAO {

    @Override
    public int insert(Jour data) {
        return super.insert(NAMESPACE.concat("insert_jour"), data);
    }

    @Override
    public int delete(Jour data) {
        return 0;
    }

    @Override
    public Jour select(Jour condition) {
        return super.select(NAMESPACE.concat("select_jour"), condition,
            Jour.class);
    }

    @Override
    public long selectTotalCount(Jour condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_jour_count"),
            condition);
    }

    @Override
    public List<Jour> selectList(Jour condition) {
        return super.selectList(NAMESPACE.concat("select_jour"), condition,
            Jour.class);
    }

    @Override
    public List<Jour> selectList(Jour condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_jour"), start, count,
            condition, Jour.class);
    }

    @Override
    public int checkJour(Jour data) {
        return super.update(NAMESPACE.concat("update_check_jour"), data);
    }

    @Override
    public int adjustJour(Jour data) {
        return super.update(NAMESPACE.concat("update_adjust_jour"), data);
    }

    @Override
    public long selectTotalAmount(Jour data) {
        return super.selectTotalCount(NAMESPACE.concat("select_totalAmount"),
            data);
    }

    @Override
    public List<Jour> selectJourDetailPage(int pageNO, int pageSize,
            Jour condition) {
        return super.selectList(NAMESPACE.concat("select_jour_detail"), pageNO,
            pageSize, condition, Jour.class);
    }
}
