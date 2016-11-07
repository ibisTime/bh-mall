package com.std.user.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICompanyCertificateAO;
import com.std.user.bo.ICertificateBO;
import com.std.user.bo.ICompanyBO;
import com.std.user.bo.ICompanyCertificateBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.Certificate;
import com.std.user.domain.Company;
import com.std.user.domain.CompanyCertificate;
import com.std.user.exception.BizException;

@Service
public class CompanyCertificateAOImpl implements ICompanyCertificateAO {

    @Autowired
    private ICompanyCertificateBO companyCertificateBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ICertificateBO certificateBO;

    @Override
    public String addCompanyCertificate(CompanyCertificate data) {
        return companyCertificateBO.saveCompanyCertificate(data);
    }

    @Override
    public int editCompanyCertificate(CompanyCertificate data) {
        if (!companyCertificateBO.isCompanyCertificateExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return companyCertificateBO.refreshCompanyCertificate(data);
    }

    @Override
    public int dropCompanyCertificate(String code) {
        if (!companyCertificateBO.isCompanyCertificateExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return companyCertificateBO.removeCompanyCertificate(code);
    }

    @Override
    public Paginable<CompanyCertificate> queryCompanyCertificatePage(int start,
            int limit, CompanyCertificate condition) {
        Paginable<CompanyCertificate> page = companyCertificateBO.getPaginable(
            start, limit, condition);
        List<CompanyCertificate> list = page.getList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (CompanyCertificate data : list) {
                Company company = companyBO.getCompany(data.getCompanyCode());
                data.setCompany(company);
            }
        }
        return page;
    }

    @Override
    public List<CompanyCertificate> queryCompanyCertificateList(
            CompanyCertificate condition) {
        return companyCertificateBO.queryCompanyCertificateList(condition);
    }

    @Override
    public CompanyCertificate getCompanyCertificate(String code) {
        CompanyCertificate data = companyCertificateBO
            .getCompanyCertificate(code);
        Company company = companyBO.getCompany(data.getCompanyCode());
        data.setCompany(company);
        Certificate certificate = certificateBO.getCertificate(data
            .getCertificateCode());
        data.setCertificate(certificate);
        return data;
    }
}
