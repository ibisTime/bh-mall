package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Jour;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 下午2:40:13 
 * @history:
 */
public interface IJourBO extends IPaginableBO<Jour> {

    // 正常新增
    public String addJour(Account dbAccount, EChannelType channelType,
            String channelOrder, String payGroup, String refNo,
            EBizType bizType, String bizNote, Long transAmount);

    public List<Jour> queryJourList(Jour condition);

    public Jour getJour(String code);

    public Jour getJourNotException(String code);

    public Long getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd);

    public Long getTotalAmount(String bizType, String accountNumber);

    public List<Jour> queryJourByAccountPage(int pageNO, int pageSize,
            Jour condition);

}
