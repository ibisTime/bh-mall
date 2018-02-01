package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Material;

public interface IMaterialDAO extends IBaseDAO<Material> {

    String NAMESPACE = IMaterialDAO.class.getName().concat(".");

    /**
     * 更新素材
     * @param data 
     * @create: 2018年2月1日 上午10:01:48 nyc
     * @history:
     */
    public void update(Material data);

}
