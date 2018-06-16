package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IChannelBankAO;
import com.bh.mall.bo.IChannelBankBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChannelBank;
import com.bh.mall.exception.BizException;

@Service
public class ChannelBankAOImpl implements IChannelBankAO {

    @Autowired
    private IChannelBankBO channelBankBO;

    @Override
    public int addChannelBank(String bankCode, String bankName) {
        return channelBankBO.saveChannelBank(bankCode, bankName);
    }

    @Override
    public void editChannelBank(String id, String bankCode, String bankName) {
        ChannelBank data = channelBankBO
            .getChannelBank(StringValidater.toLong(id));
        channelBankBO.refreshChannelBank(data, bankCode, bankName);
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
        return channelBankBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ChannelBank> queryChannelBankList(ChannelBank condition) {
        return channelBankBO.queryChannelBankList(condition);
    }

    @Override
    public ChannelBank getChannelBank(Long id) {
        return channelBankBO.getChannelBank(id);
    }
}
