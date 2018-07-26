package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ProCode;

//dao层 
public interface IProCodeDAO extends IBaseDAO<ProCode> {

    String NAMESPACE = IProCodeDAO.class.getName().concat(".");

    void update(ProCode data);

    List<ProCode> selectCodeList(ProCode condition);

    void splitSingle(ProCode barData);

}
