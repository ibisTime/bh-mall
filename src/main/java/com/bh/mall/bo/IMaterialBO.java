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

    /**
    * 判断分页查询输入的条件是否存在
    * @param type
    * @param title
    * @param status
    * @return 
    * @create: 2018年2月1日 下午7:10:02 nyc
    * @history:
    */
    public List<Material> checkCondition(String type, String title,
            String status);
}
