package com.bh.mall.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IMaterialBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IMaterialDAO;
import com.bh.mall.domain.Material;

@Component
public class MaterialBOImpl extends PaginableBOImpl<Material> implements IMaterialBO {

	@Autowired
	private IMaterialDAO materialDAO;

	@Override
	public int insert(Material data) {
		return materialDAO.insert(data);
	}

	@Override
	public int update(Material data) {
		return materialDAO.update(data);
	}

	@Override
	public int delete(Material condition) {
		return materialDAO.delete(condition);
	}



	@Override
	public Material getMaterial(String code) {
		// XXX Auto-generated method stub
		return null;
	}
}
