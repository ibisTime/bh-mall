package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Award;

public interface IAwardDAO extends IBaseDAO<Award> {
    String NAMESPACE = IAwardDAO.class.getName().concat(".");

    void update(Award data);
}
