package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.ChannelBank;

/**
 * @author: xieyj 
 * @since: 2016年11月10日 下午8:26:58 
 * @history:
 */
public interface IChannelBankDAO extends IBaseDAO<ChannelBank> {
    String NAMESPACE = IChannelBankDAO.class.getName().concat(".");

    /**
     * 更新渠道银行
     * @param data
     * @return 
     * @create: 2016年11月10日 下午7:46:43 xieyj
     * @history:
     */
    public int update(ChannelBank data);
}
