package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.InnerSpecs;

public interface IInnerSpecsDAO extends IBaseDAO<InnerSpecs> {
    String NAMESPACE = IInnerSpecsDAO.class.getName().concat(".");

    public int update(InnerSpecs data);
}
