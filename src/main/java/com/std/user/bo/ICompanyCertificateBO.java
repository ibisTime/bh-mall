package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.CompanyCertificate;

public interface ICompanyCertificateBO extends IPaginableBO<CompanyCertificate> {

    public boolean isCompanyCertificateExist(String code);

    public String saveCompanyCertificate(CompanyCertificate data);

    public int removeCompanyCertificate(String code);

    public int refreshCompanyCertificate(CompanyCertificate data);

    public List<CompanyCertificate> queryCompanyCertificateList(
            CompanyCertificate condition);

    public CompanyCertificate getCompanyCertificate(String code);

}
