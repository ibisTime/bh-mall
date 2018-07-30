package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SpecsLog;

//dao层 
public interface ISpecsLogDAO extends IBaseDAO<SpecsLog> {
	String NAMESPACE = ISpecsLogDAO.class.getName().concat(".");
}