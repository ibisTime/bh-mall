package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.JsAward;

//dao层 
public interface IJsAwardDAO extends IBaseDAO<JsAward> {
    String NAMESPACE = IJsAwardDAO.class.getName().concat(".");

    int update(JsAward data);
}
