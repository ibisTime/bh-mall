package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ChangeProduct;

//dao层 
public interface IChangeProductDAO extends IBaseDAO<ChangeProduct> {
	String NAMESPACE = IChangeProductDAO.class.getName().concat(".");
}