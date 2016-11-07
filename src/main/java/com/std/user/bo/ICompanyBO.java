package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.Company;

public interface ICompanyBO extends IPaginableBO<Company> {

    public boolean isCompanyExist(String code);

    public void checkLoginName(String loginName);

    public String saveCompany(Company data);

    public int removeCompany(String code);

    public int refreshCompany(Company data);

    public int refreshCompanyLocation(String code, String updater,
            String remark, String location);

    public int refreshCompanyDefault(String code);

    public int refreshCompanyHot(Company data);

    public int refreshCompanyPsw(String code, String password);

    public List<Company> queryCompanyList(Company condition);

    public Paginable<Company> getPaginableJJ(int start, int pageSize,
            Company condition);

    public Company getCompany(String code);

    public Company getCompanyByUserId(String userId);

    public Company getCompanyByDomain(String domain);

    public Company getDefaultCompany();

}
