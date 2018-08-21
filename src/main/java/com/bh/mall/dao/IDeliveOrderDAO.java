package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.DeliveOrder;

//dao层 
public interface IDeliveOrderDAO extends IBaseDAO<DeliveOrder> {
	String NAMESPACE = IDeliveOrderDAO.class.getName().concat(".");
}