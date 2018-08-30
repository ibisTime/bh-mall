package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IChannelBankAO;
import com.bh.mall.bo.IChannelBankBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChannelBank;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.exception.BizException;

@Service
public class ChannelBankAOImpl implements IChannelBankAO {

    @Autowired
    private IChannelBankBO channelBankBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public int addChannelBank(String bankCode, String bankName,
            String updater) {
        return channelBankBO.saveChannelBank(bankCode, bankName, updater);
    }

    @Override
    public void editChannelBank(String id, String bankCode, String bankName,
            String updater, String remark) {
        ChannelBank data = channelBankBO
            .getChannelBank(StringValidater.toLong(id));
        channelBankBO.refreshChannelBank(data, bankCode, bankName, updater,
            remark);
    }

    @Override
    public void dropChannelBank(Long id) {
        if (!channelBankBO.isChannelBankExist(id)) {
            throw new BizException("xn0000", "渠道银行序号不存在");
        }
        channelBankBO.removeChannelBank(id);
    }

    @Override
    public Paginable<ChannelBank> queryChannelBankPage(int start, int limit,
            ChannelBank condition) {
        Paginable<ChannelBank> page = channelBankBO.getPaginable(start, limit,
            condition);
        for (ChannelBank data : page.getList()) {
            if (StringUtils.isNotBlank(data.getUpdater())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
                data.setUpdateName(sysUser.getRealName());
            }
        }

        return page;
    }

    @Override
    public List<ChannelBank> queryChannelBankList(ChannelBank condition) {
        List<ChannelBank> list = channelBankBO.queryChannelBankList(condition);
        for (ChannelBank data : list) {
            if (StringUtils.isNotBlank(data.getUpdater())) {
                if (StringUtils.isNotBlank(data.getUpdater())) {
                    SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
                    data.setUpdateName(sysUser.getRealName());
                }
            }
        }
        return list;
    }

    @Override
    public ChannelBank getChannelBank(Long id) {
        ChannelBank data = channelBankBO.getChannelBank(id);
        if (StringUtils.isNotBlank(data.getUpdater())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getUpdater());
            data.setUpdateName(sysUser.getRealName());
        }
        return data;
    }
}
