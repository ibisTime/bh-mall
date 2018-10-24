package com.xn.sdhh.dao;

import com.xn.sdhh.dao.base.IBaseDAO;
import com.xn.sdhh.domain.Business;

public interface IBusinessDAO extends IBaseDAO<Business> {

    String NAMESPACE = IBusinessDAO.class.getName().concat(".");

    void update(Business data);

}
