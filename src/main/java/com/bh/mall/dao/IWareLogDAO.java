package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.WareLog;

//dao层 
public interface IWareLogDAO extends IBaseDAO<WareLog> {
	String NAMESPACE = IWareLogDAO.class.getName().concat(".");
}