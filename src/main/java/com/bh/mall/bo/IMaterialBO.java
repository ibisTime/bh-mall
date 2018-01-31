package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.domain.Material;

public interface IMaterialBO {

	public int insert(Material data);
	
	public int update(Material data);
	
	public int delete(Material condition);
	
	
	public Material getMaterial(String code);
}
