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
import com.std.user.enums.EComCertificateStatus;
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
    public int dropCompanyCertificate(String code) {
        CompanyCertificate data = companyCertificateBO
            .getCompanyCertificate(code);
        if (EComCertificateStatus.APPROVE_YES.getCode()
            .equals(data.getStatus())) {
            throw new BizException("xn0000", "资质申请已通过，无法删除");
        }
        return companyCertificateBO.removeCompanyCertificate(code);
    }

    @Override
    public int editCompanyCertificate(CompanyCertificate data) {
        if (!companyCertificateBO.isCompanyCertificateExist(data.getCode())) {
            throw new BizException("xn0000", "该编号不存在");
        }
        if (EComCertificateStatus.APPROVE_YES.getCode()
            .equals(data.getStatus())) {
            throw new BizException("xn0000", "该资质申请已通过，无需修改");
        }
        return companyCertificateBO.refreshCompanyCertificate(data);
    }

    @Override
    public int approveCompanyCertificate(String code, String approver,
            String approveResult, String approveNote) {
        CompanyCertificate data = companyCertificateBO
            .getCompanyCertificate(code);
        if (!EComCertificateStatus.TOAPPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该申请资质不是申请状态，无法审核");
        }
        return companyCertificateBO.refreshCompanyCertificateStatus(code,
            approver, approveResult, approveNote);
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
