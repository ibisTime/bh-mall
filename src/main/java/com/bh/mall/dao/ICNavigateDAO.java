package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.CNavigate;

public interface ICNavigateDAO extends IBaseDAO<CNavigate> {
    String NAMESPACE = ICNavigateDAO.class.getName().concat(".");

    /**
     * 更新C端导航
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:33:49 LENOVO
     * @history:
     */
    public int update(CNavigate data);

}
