package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ICompanyChannelAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.ICompanyChannelBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.CompanyChannel;
import com.bh.mall.exception.BizException;

@Service
public class CompanyChannelAOImpl implements ICompanyChannelAO {

    @Autowired
    private ICompanyChannelBO companyChannelBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public void addCompanyChannel(CompanyChannel data) {
        long count = companyChannelBO.getCompanyChannelCount(
            data.getCompanyCode(), data.getChannelType());
        if (count > 0) {
            throw new BizException("xn0000", "公司渠道已存在");
        }
        companyChannelBO.saveCompanyChannel(data);
    }

    @Override
    public void dropCompanyChannel(Long id) {
        if (!companyChannelBO.isCompanyChannelExist(id)) {
            throw new BizException("xn0000", "公司渠道序号不存在");
        }
        companyChannelBO.removeCompanyChannel(id);
    }

    public void editCompanyChannel(CompanyChannel data) {
        if (!companyChannelBO.isCompanyChannelExist(data.getId())) {
            throw new BizException("xn0000", "公司渠道序号不存在");
        }
        long count = companyChannelBO.getCompanyChannelCount(
            data.getCompanyCode(), data.getChannelType());
        if (count > 1) {
            throw new BizException("xn0000", "公司渠道已存在");
        }
        companyChannelBO.refreshCompanyChannel(data);
    }

    @Override
    public Paginable<CompanyChannel> queryCompanyChannelPage(int start,
            int limit, CompanyChannel condition) {
        return companyChannelBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CompanyChannel> queryCompanyChannelList(CompanyChannel condition) {
        return companyChannelBO.queryCompanyChannelList(condition);
    }

    @Override
    public CompanyChannel getCompanyChannel(Long id) {
        return companyChannelBO.getCompanyChannel(id);
    }
}
