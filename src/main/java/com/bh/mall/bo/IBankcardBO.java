package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Bankcard;

/**
 * 银行卡
 * @author: LENOVO 
 * @since: 2018年8月1日 下午2:23:25 
 * @history:
 */
public interface IBankcardBO extends IPaginableBO<Bankcard> {

    // 根据编号查询是否存在
    public boolean isBankcardExist(String code);

    // 新增银行卡
    public String saveBankcard(Bankcard data);

    // 删除银行卡
    public int removeBankcard(String code);

    // 更新
    public int refreshBankcard(Bankcard data);

    // 列表查询
    public List<Bankcard> queryBankcardList(Bankcard condition);

    // 详情查询
    public Bankcard getBankcard(String code);

    // 根据银行卡编号查询
    public Bankcard getBankcardByBankcardNumber(String bankcardNumber);

    // 更具用户编号查询
    public List<Bankcard> queryBankcardList(String userId);
}
