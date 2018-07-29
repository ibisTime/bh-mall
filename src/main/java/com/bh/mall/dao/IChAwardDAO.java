package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ChAward;

//dao层 
public interface IChAwardDAO extends IBaseDAO<ChAward> {
    String NAMESPACE = IChAwardDAO.class.getName().concat(".");

    void update(ChAward data);
}
