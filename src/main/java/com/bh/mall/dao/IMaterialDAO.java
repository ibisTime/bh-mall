package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Material;

public interface IMaterialDAO extends IBaseDAO<Material> {
	
	String NAMESPACE = IMaterialDAO.class.getName().concat(".");
	

}
