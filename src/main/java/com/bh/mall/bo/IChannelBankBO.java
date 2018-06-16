package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.ChannelBank;

/**
 * @author: xieyj 
 * @since: 2016年11月10日 下午8:30:39 
 * @history:
 */
public interface IChannelBankBO extends IPaginableBO<ChannelBank> {

    public boolean isChannelBankExist(Long id);

    public int saveChannelBank(String bankCode, String bankName);

    public int removeChannelBank(Long id);

    public List<ChannelBank> queryChannelBankList(ChannelBank condition);

    public ChannelBank getChannelBank(Long id);

    public ChannelBank getChannelBank(String bankCode);

    public void refreshChannelBank(ChannelBank data, String bankCode,
            String bankName);

}
