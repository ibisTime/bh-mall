package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ChannelBank;

public interface IChannelBankAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public int addChannelBank(String bankCode, String bankName);

    public void dropChannelBank(Long id);

    public Paginable<ChannelBank> queryChannelBankPage(int start, int limit,
            ChannelBank condition);

    public List<ChannelBank> queryChannelBankList(ChannelBank condition);

    public ChannelBank getChannelBank(Long id);

    public void editChannelBank(String id, String bankCode, String bankName);

}
