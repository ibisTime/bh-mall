package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.TjAward;

public interface ITjAwardDAO extends IBaseDAO<TjAward> {
    String NAMESPACE = ITjAwardDAO.class.getName().concat(".");

    void update(TjAward data);
}
