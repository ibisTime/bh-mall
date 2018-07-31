package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ChAward;

//dao层 
public interface IChAwardDAO extends IBaseDAO<ChAward> {
    String NAMESPACE = IChAwardDAO.class.getName().concat(".");

    /**
     * 更新出货奖励
     * @param data 
     * @create: 2018年7月31日 下午2:33:19 LENOVO
     * @history:
     */
    void update(ChAward data);
}
