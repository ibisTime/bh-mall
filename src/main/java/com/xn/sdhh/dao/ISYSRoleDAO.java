package com.xn.sdhh.dao;

import com.xn.sdhh.dao.base.IBaseDAO;
import com.xn.sdhh.domain.SYSRole;

public interface ISYSRoleDAO extends IBaseDAO<SYSRole> {
    String NAMESPACE = ISYSRoleDAO.class.getName().concat(".");

    public int update(SYSRole data);
}
