package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ProductLog;

//dao层 
public interface IProductLogDAO extends IBaseDAO<ProductLog> {
	String NAMESPACE = IProductLogDAO.class.getName().concat(".");
}