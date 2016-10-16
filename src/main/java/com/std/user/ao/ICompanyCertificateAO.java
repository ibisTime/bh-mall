package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.CompanyCertificate;

public interface ICompanyCertificateAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCompanyCertificate(CompanyCertificate data);

    public int dropCompanyCertificate(String code);

    public int editCompanyCertificate(CompanyCertificate data);

    public Paginable<CompanyCertificate> queryCompanyCertificatePage(int start,
            int limit, CompanyCertificate condition);

    public List<CompanyCertificate> queryCompanyCertificateList(
            CompanyCertificate condition);

    public CompanyCertificate getCompanyCertificate(String code);

}
