package com.std.user.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICompanyAO;
import com.std.user.bo.ICMenuBO;
import com.std.user.bo.ICompanyBO;
import com.std.user.bo.base.Paginable;
import com.std.user.core.OrderNoGenerater;
import com.std.user.domain.CMenu;
import com.std.user.domain.Company;
import com.std.user.exception.BizException;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ICMenuBO cMenuBO;

    @Override
    public String addCompany(Company data) {
        return companyBO.saveCompany(data);
    }

    @Override
    public String addGWCompany(Company data) {
        String code = companyBO.saveCompany(data);
        String menuCode = null;
        CMenu ind = new CMenu();
        menuCode = OrderNoGenerater.generate("ind");
        ind.setCode(menuCode);
        ind.setName("首页");
        ind.setOrderNo(0);
        ind.setParentCode("0");
        ind.setCompanyCode(code);
        cMenuBO.saveCMenu(ind);
        CMenu com = new CMenu();
        menuCode = OrderNoGenerater.generate("com");
        com.setCode(menuCode);
        com.setName("公司简介");
        com.setOrderNo(1);
        com.setParentCode("0");
        com.setCompanyCode(code);
        cMenuBO.saveCMenu(com);
        CMenu wei = new CMenu();
        menuCode = OrderNoGenerater.generate("wei");
        wei.setCode(menuCode);
        wei.setName("微信顶级菜单");
        wei.setOrderNo(1);
        wei.setParentCode("0");
        wei.setCompanyCode(code);
        cMenuBO.saveCMenu(wei);
        return code;
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
        condition.setProvinceForQuery(province);
        condition.setCityForQuery(city);
        condition.setAreaForQuery(area);
        List<Company> list = companyBO.queryCompanyList(condition);
        Company result = null;
        if (CollectionUtils.sizeIsEmpty(list)) {
            result = companyBO.getDefaultCompany();
        } else {
            result = list.get(0);
        }
        return result;
    }

    @Override
    public Company getCompanyByDomain(String domain) {
        if ("".equals(domain) || null == domain) {
            throw new BizException("xn0000", "请输入合法域名");
        }
        return companyBO.getCompanyByDomain(domain);
    }

}
