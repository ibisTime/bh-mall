package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AwardInterval;

//dao层 
public interface IAwardIntervalDAO extends IBaseDAO<AwardInterval> {
    String NAMESPACE = IAwardIntervalDAO.class.getName().concat(".");

    void update(AwardInterval data);
}
