package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Bankcard;

/**
 * 
 * @author: asus 
 * @since: 2016年12月22日 下午4:31:08 
 * @history:
 */
public interface IBankCardDAO extends IBaseDAO<Bankcard> {
    String NAMESPACE = IBankCardDAO.class.getName().concat(".");

    /**
     * 更新银行卡
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:23:35 LENOVO
     * @history:
     */
    public int update(Bankcard data);
}
