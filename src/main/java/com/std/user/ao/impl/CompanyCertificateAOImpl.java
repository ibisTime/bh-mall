package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.ICompanyCertificateAO;
import com.std.user.bo.ICompanyCertificateBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.CompanyCertificate;
import com.std.user.exception.BizException;

@Service
public class CompanyCertificateAOImpl implements ICompanyCertificateAO {

    @Autowired
    private ICompanyCertificateBO companyCertificateBO;

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
        return companyCertificateBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CompanyCertificate> queryCompanyCertificateList(
            CompanyCertificate condition) {
        return companyCertificateBO.queryCompanyCertificateList(condition);
    }

    @Override
    public CompanyCertificate getCompanyCertificate(String code) {
        return companyCertificateBO.getCompanyCertificate(code);
    }
}
