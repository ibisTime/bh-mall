package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Intro;

//dao层 
public interface IIntroDAO extends IBaseDAO<Intro> {
    String NAMESPACE = IIntroDAO.class.getName().concat(".");

    int update(Intro data);
}
