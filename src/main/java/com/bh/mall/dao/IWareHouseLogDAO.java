package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.WareHouseLog;

//dao层 
public interface IWareHouseLogDAO extends IBaseDAO<WareHouseLog> {
	String NAMESPACE = IWareHouseLogDAO.class.getName().concat(".");
}