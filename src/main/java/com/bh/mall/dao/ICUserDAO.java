package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.CUser;

public interface ICUserDAO extends IBaseDAO<CUser> {
    String NAMESPACE = ICUserDAO.class.getName().concat(".");

    /**
     * 更新状态 
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:34:38 LENOVO
     * @history:
     */
    public int updateStatus(CUser data);

    /**
     * 更新昵称 
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:34:48 LENOVO
     * @history:
     */
    public int updateNickname(CUser data);

    /**
     * 更新更新c端
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:36:07 LENOVO
     * @history:
     */
    public int update(CUser data);

    /**
     * 更新更新c端wx
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:37:13 LENOVO
     * @history:
     */
    public int updateWxInfo(CUser data);

}
