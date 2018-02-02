package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Material;

public interface IMaterialBO extends IPaginableBO<Material> {

    public void addMaterial(Material data);

    public Material getMaterial(String code);

    public void editMaterial(Material data);

    public void dropMaterial(String code);

    public List<Material> queryMaterialList(Material condition);

}
