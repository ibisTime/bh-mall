package com.std.user.dao;

import com.std.user.dao.base.IBaseDAO;
import com.std.user.domain.CMenu;

public interface ICMenuDAO extends IBaseDAO<CMenu> {
    String NAMESPACE = ICMenuDAO.class.getName().concat(".");

    public int update(CMenu data);

    public int updateStatus(CMenu data);
}
