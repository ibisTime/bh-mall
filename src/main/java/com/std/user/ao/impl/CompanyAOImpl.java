package com.std.user.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.user.ao.ICompanyAO;
import com.std.user.bo.ICNavigateBO;
import com.std.user.bo.ICompanyBO;
import com.std.user.bo.base.Paginable;
import com.std.user.core.OrderNoGenerater;
import com.std.user.domain.CNavigate;
import com.std.user.domain.Company;
import com.std.user.enums.EBoolean;
import com.std.user.enums.ECNavigateType;
import com.std.user.exception.BizException;
import com.std.user.util.PinYin;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ICNavigateBO cNavigateBO;

    @Override
    public String addCompany(Company data) {
        return companyBO.saveCompany(data);
    }

    @Override
    @Transactional
    public String addGWCompany(Company data) {
        String code = companyBO.saveCompany(data);

        addMenu("ind", "首页", code);
        addMenu("inw", "微信首页", code);
        addMenu("com", "公司简介", code);
        addMenu("cin", "我要合作", code);
        addMenu("wei", "微信顶级菜单", code);
        return code;
    }

    private void addMenu(String prefix, String name, String companyCode) {
        String code = null;
        CNavigate cn = new CNavigate();
        code = OrderNoGenerater.generate(prefix);
        cn.setCode(code);
        cn.setName(name);
        cn.setType(ECNavigateType.CAIDAN.getCode());
        cn.setStatus(EBoolean.YES.getCode());
        cn.setOrderNo(1);
        cn.setParentCode(EBoolean.NO.getCode());
        cn.setCompanyCode(companyCode);
        cNavigateBO.saveCNavigate(cn);
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
        List<Company> list = companyBO.queryCompanyList(condition);
        // 将结果按首字母排序
        return sortByFirstLetter(list);
    }

    // 将结果按首字母排序
    private List<Company> sortByFirstLetter(List<Company> list) {
        List<Company> result = new ArrayList<>();
        // 用来记录result的长度
        int i = 0;
        // 用来判断该元素是否已添加
        boolean isAdd = false;
        // 遍历待排序数组
        for (Company company : list) {
            isAdd = false;
            if (i == 0) {
                result.add(company);
                i++;
            } else {
                // 遍历已排序数组
                for (int j = 0; j < i; j++) {
                    // 若待排序元素的首字母小于其元素，则将待排序元素插入到其位置
                    if (PinYin.cn2py(company.getName()).charAt(0) < PinYin
                        .cn2py(result.get(j).getName()).charAt(0)) {
                        result.add(j, company);
                        isAdd = true;
                        i++;
                        break;
                    }
                }
                if (!isAdd) {
                    result.add(company);
                }
            }
        }
        return result;
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
        int count = 0;
        Company company = companyBO.getCompany(code);
        if (company.getLocation().equals(EBoolean.NO.getCode())) {
            count = companyBO.refreshCompanyLocation(code, updater, remark,
                EBoolean.YES.getCode());
        } else {
            count = companyBO.refreshCompanyLocation(code, updater, remark,
                EBoolean.NO.getCode());
        }
        return count;
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
        Company result = new Company();
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
