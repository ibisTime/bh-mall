package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ChannelBank;

/**
 * 渠道银行
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:59:26 
 * @history:
 */
public interface IChannelBankAO {

    static final String DEFAULT_ORDER_COLUMN = "id";

    // 添加渠道银行
    public int addChannelBank(String bankCode, String bankName, String updater);

    // 删除渠道银行
    public void dropChannelBank(Long id);

    // 修改渠道银行
    public void editChannelBank(String id, String bankCode, String bankName,
            String updater, String remark);

    // 分页查询
    public Paginable<ChannelBank> queryChannelBankPage(int start, int limit,
            ChannelBank condition);

    // 列表查询
    public List<ChannelBank> queryChannelBankList(ChannelBank condition);

    // 根据编号查询
    public ChannelBank getChannelBank(Long id);

}
