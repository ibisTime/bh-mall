package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Specs;

public interface ISpecsDAO extends IBaseDAO<Specs> {
    String NAMESPACE = ISpecsDAO.class.getName().concat(".");

    void update(Specs data);

    void deleteByProdut(Specs data);

    void updateRepertory(Specs data);

    List<Specs> selectSpecsListByB(Specs condition);

    List<Specs> selectSpecsPage(int start, int pageSize, Specs condition);

    long selectTotalCountC(Specs condition);
}
