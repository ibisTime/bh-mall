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

    /**
     * 上架
     * @param data 
     * @create: 2018年8月10日 下午3:33:39 nyc
     * @history:
     */
    public void putOn(CNavigate data);

    /**
     * 下架
     * @param data 
     * @create: 2018年8月10日 下午3:33:39 nyc
     * @history:
     */
    public void putDown(CNavigate data);

}
