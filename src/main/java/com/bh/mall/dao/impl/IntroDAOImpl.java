package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IIntroDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Intro;

@Repository("introDAOImpl")
public class IntroDAOImpl extends AMybatisTemplate implements IIntroDAO {

    @Override
    public int insert(Intro data) {
        return super.insert(NAMESPACE.concat("insert_intro"), data);
    }

    @Override
    public int delete(Intro data) {
        return super.delete(NAMESPACE.concat("delete_intro"), data);
    }

    @Override
    public Intro select(Intro condition) {
        return super.select(NAMESPACE.concat("select_intro"), condition,
            Intro.class);
    }

    @Override
    public long selectTotalCount(Intro condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_intro_count"),
            condition);
    }

    @Override
    public List<Intro> selectList(Intro condition) {
        return super.selectList(NAMESPACE.concat("select_intro"), condition,
            Intro.class);
    }

    @Override
    public List<Intro> selectList(Intro condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_intro"), start, count,
            condition, Intro.class);
    }

}
