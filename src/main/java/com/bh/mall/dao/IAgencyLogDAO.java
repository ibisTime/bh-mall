package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgencyLog;

//dao层 
public interface IAgencyLogDAO extends IBaseDAO<AgencyLog> {
	String NAMESPACE = IAgencyLogDAO.class.getName().concat(".");
}