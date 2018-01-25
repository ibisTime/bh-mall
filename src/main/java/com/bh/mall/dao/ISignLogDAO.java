package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.SignLog;

/** 
 * 签到DAO层
 * @author: zuixian 
 * @since: 2016年9月18日 下午7:12:19 
 * @history:
 */
public interface ISignLogDAO extends IBaseDAO<SignLog> {
    String NAMESPACE = ISignLogDAO.class.getName().concat(".");
}
