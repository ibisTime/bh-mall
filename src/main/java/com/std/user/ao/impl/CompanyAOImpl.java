package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICompanyAO;
import com.std.user.bo.ICompanyBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.Company;
import com.std.user.enums.EBoolean;
import com.std.user.exception.BizException;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addCompany(Company data) {
        return companyBO.saveCompany(data);
    }

    @Override
    public int editCompany(Company data) {
        if (!companyBO.isCompanyExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return companyBO.refreshCompany(data);
    }

    @Override
    public int dropCompany(String code) {
        if (!companyBO.isCompanyExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return companyBO.removeCompany(code);
    }

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyBO.queryCompanyList(condition);
    }

    @Override
    public Company getCompany(String code) {
        return companyBO.getCompany(code);
    }

    @Override
    public int editCompanyLocation(String code, String updater, String remark) {
        if (!companyBO.isCompanyExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return companyBO.refreshCompanyLocation(code, updater, remark);
    }

    @Override
    public int editCompanyDefault(String code) {
        if (!companyBO.isCompanyExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return companyBO.refreshCompanyDefault(code);
    }

    @Override
    public Company getCompanyByUserId(String userId) {
        return companyBO.getCompanyByUserId(userId);
    }

    @Override
    public Company getCompanyByPCA(String province, String city, String area) {
        Company condition = new Company();
        condition.setProvince(province);
        condition.setCity(city);
        condition.setArea(area);
        List<Company> list = companyBO.queryCompanyList(condition);
        // 若该地区无公司，则返回默认公司
        if (list == null) {
            // 设置查询默认公司条件
            Company company = new Company();
            company.setIsDefault(EBoolean.YES.getCode());
            List<Company> list1 = companyBO.queryCompanyList(company);
            return list1.get(0);
        }
        return list.get(0);
    }
}
