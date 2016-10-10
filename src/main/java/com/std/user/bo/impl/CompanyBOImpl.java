package com.std.user.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.ICompanyBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.core.EGeneratePrefix;
import com.std.user.core.OrderNoGenerater;
import com.std.user.dao.ICompanyDAO;
import com.std.user.domain.Company;
import com.std.user.enums.EBoolean;
import com.std.user.exception.BizException;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company> implements
        ICompanyBO {

    @Autowired
    private ICompanyDAO companyDAO;

    @Override
    public boolean isCompanyExist(String code) {
        Company condition = new Company();
        condition.setCode(code);
        if (companyDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveCompany(Company data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.COM.getCode());
            data.setCode(code);
            data.setUpdateDatetime(new Date());
            companyDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCompany(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Company data = new Company();
            data.setCode(code);
            count = companyDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCompany(Company data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setUpdateDatetime(new Date());
            count = companyDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyDAO.selectList(condition);
    }

    @Override
    public Company getCompany(String code) {
        Company data = null;
        if (StringUtils.isNotBlank(code)) {
            Company condition = new Company();
            condition.setCode(code);
            data = companyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该编号不存在");
            }
        }
        return data;
    }

    @Override
    public int refreshCompanyLocation(String code, String updater, String remark) {
        Company data = new Company();
        data.setCode(code);
        data.setLocation(EBoolean.NO.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        return companyDAO.updateLocation(data);
    }

    @Override
    public int refreshCompanyDefault(String code) {
        Company data = new Company();
        int count = 0;
        if (code != null && code != "") {
            data.setCode(code);
            // 先找出默认的公司
            Company company1 = new Company();
            company1.setIsDefault("1");
            Company condition = companyDAO.select(company1);
            // 若原本就有默认公司
            if (condition != null) {
                // 如果默认公司与目的公司相同，则将取消其默认
                // 如果不相同，则取消默认公司的默认，将目的公司设置为默认
                if (condition.getCode().equals(data.getCode())) {
                    data.setIsDefault("0");
                    count = companyDAO.updateDefault(data);
                } else {
                    condition.setIsDefault("0");
                    companyDAO.updateDefault(condition);
                    data.setIsDefault("1");
                    count = companyDAO.updateDefault(data);
                }
            } else {
                // 若原本无默认公司，则直接将目的公司设置为默认
                data.setIsDefault("1");
                count = companyDAO.updateDefault(data);
            }
            // 效果：至多只有一个默认公司
        }
        return count;
    }

    @Override
    public Company getCompanyByUserId(String userId) {
        Company data = null;
        if (StringUtils.isNotBlank(userId)) {
            Company condition = new Company();
            condition.setUserId(userId);
            data = companyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该用户编号不存在公司");
            }
        }
        return data;
    }
}
