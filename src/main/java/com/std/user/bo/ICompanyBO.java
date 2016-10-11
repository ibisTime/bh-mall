package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.Company;

public interface ICompanyBO extends IPaginableBO<Company> {

    public boolean isCompanyExist(String code);

    public String saveCompany(Company data);

    public int removeCompany(String code);

    public int refreshCompany(Company data);

    public int refreshCompanyLocation(String code, String updater, String remark);

    public int refreshCompanyDefault(String code);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

    public Company getCompanyByUserId(String userId);

    public Company getDefaultCompany();

}
