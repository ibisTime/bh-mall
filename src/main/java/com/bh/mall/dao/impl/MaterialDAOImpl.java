package com.bh.mall.dao.impl;

import java.util.List;

import com.bh.mall.dao.IMaterialDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Material;

public class MaterialDAOImpl extends AMybatisTemplate implements IMaterialDAO {

	@Override
	public int insert(Material data) {
		return super.insert(NAMESPACE.concat("insert_material"), data);
	}

	@Override
	public int delete(Material data) {
		return super.delete(NAMESPACE.concat("delete_material"), data);
	}

	@Override
	public Material select(Material condition) {
		return super.select(NAMESPACE.concat("select_material"), condition, Material.class);
	}

	@Override
	public long selectTotalCount(Material condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_count"), condition);
	}

	@Override
	public List<Material> selectList(Material condition) {
		return super.selectList(NAMESPACE.concat("select_count"), condition, Material.class);
	}

	@Override
	public List<Material> selectList(Material condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_count"), start, count, condition, Material.class);
	}

}
