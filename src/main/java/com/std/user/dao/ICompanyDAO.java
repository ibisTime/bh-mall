package com.std.user.dao;

import com.std.user.dao.base.IBaseDAO;
import com.std.user.domain.Company;

public interface ICompanyDAO extends IBaseDAO<Company> {
    String NAMESPACE = ICompanyDAO.class.getName().concat(".");

    public int update(Company data);

    public int updateLocation(Company data);

    public int updateDefault(Company data);
}
