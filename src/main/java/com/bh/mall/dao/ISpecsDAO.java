package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Specs;

public interface ISpecsDAO extends IBaseDAO<Specs> {
    String NAMESPACE = ISpecsDAO.class.getName().concat(".");

    void update(Specs data);

    void deleteByProdut(Specs data);
}
