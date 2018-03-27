package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Bankcard;

public interface IBankcardBO extends IPaginableBO<Bankcard> {

    public boolean isBankcardExist(String code);

    public String saveBankcard(Bankcard data);

    public int removeBankcard(String code);

    public int refreshBankcard(Bankcard data);

    public List<Bankcard> queryBankcardList(Bankcard condition);

    public Bankcard getBankcard(String code);

    public Bankcard getBankcardInfo(String code);

    public Bankcard getBankcardByBankcardNumber(String bankcardNumber);

    public List<Bankcard> queryBankcardList(String userId);
}
