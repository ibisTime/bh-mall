package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.Company;

public interface ICompanyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCompany(Company data);

    public int dropCompany(String code);

    public int editCompany(Company data);

    // 禁用公司
    public int editCompanyLocation(String code, String updater, String remark);

    // 设置默认
    public int editCompanyDefault(String code);

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    // 通过省市区来查询公司信息
    public Company getCompanyByPCA(String province, String city, String area);

    // 通过公司编号来查询公司信息
    public Company getCompany(String code);

    // 通过用户ID来查询公司信息
    public Company getCompanyByUserId(String userId);

    // 通过域名来查询公司信息
    public Company getCompanyByDomain(String domain);

}
