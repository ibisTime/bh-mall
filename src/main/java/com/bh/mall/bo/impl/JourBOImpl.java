/**
 * @Title AJourBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:07 
 * @version V1.0   
 */
package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IJourBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.common.DateUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IJourDAO;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Jour;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.EJourStatus;
import com.bh.mall.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:07 
 * @history:
 */
@Component
public class JourBOImpl extends PaginableBOImpl<Jour> implements IJourBO {
    @Autowired
    private IJourDAO jourDAO;

    @Override
    public String addJour(Account dbAccount, EChannelType channelType,
            String channelOrder, String payGroup, String refNo,
            EBizType bizType, String bizNote, Long transAmount, String remark) {
        if (!EChannelType.Offline.getCode().equals(channelType.getCode())
                && !EChannelType.NBZ.getCode().equals(channelType.getCode())) {// 线下和内部帐可为空，线上必须有
            if (StringUtils.isBlank(payGroup)) {// 必须要有的判断。每一次流水新增，必有有对应业务分组
                throw new BizException("xn000000", "新增流水业务分组不能为空");
            }
        }
        if (StringUtils.isBlank(refNo)) {// 必须要有的判断。每一次流水新增，必有有对应流水分组
            throw new BizException("xn000000", "新增流关联编号不能为空");
        }
        Long amount = dbAccount.getAmount();
        if (null == amount) {
            amount = 0L;
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());

        Jour data = new Jour();
        data.setCode(code);

        data.setRefNo(refNo);
        data.setPayGroup(payGroup);
        data.setChannelOrder(channelOrder);// 内部转账时为空，外部转账时必定有
        data.setAccountNumber(dbAccount.getAccountNumber());
        data.setTransAmount(transAmount);

        data.setUserId(dbAccount.getUserId());
        data.setRealName(dbAccount.getRealName());
        data.setType(dbAccount.getType());
        data.setCurrency(dbAccount.getCurrency());
        data.setBizType(bizType.getCode());

        data.setBizNote(bizNote);
        data.setPreAmount(amount);
        data.setPostAmount(amount + transAmount);
        data.setStatus(EJourStatus.todoCheck.getCode());
        data.setRemark(remark);

        data.setCreateDatetime(new Date());
        data.setChannelType(channelType.getCode());
        jourDAO.insert(data);
        return code;
    }

    @Override
    public Jour getJour(String code) {
        Jour data = null;
        if (StringUtils.isNotBlank(code)) {
            Jour condition = new Jour();
            condition.setCode(code);
            data = jourDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000000", "单号不存在");
            }
        }
        return data;
    }

    @Override
    public Jour getJourNotException(String code) {
        Jour data = null;
        if (StringUtils.isNotBlank(code)) {
            Jour condition = new Jour();
            condition.setCode(code);
            data = jourDAO.select(condition);
        }
        return data;
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        return jourDAO.selectList(condition);
    }

    @Override
    public Long getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd) {
        Jour jour = new Jour();
        jour.setBizType(bizType);
        jour.setChannelType(channelType);
        jour.setAccountNumber(accountNumber);
        jour.setCreateDatetimeStart(DateUtil.getFrontDate(dateStart, false));
        jour.setCreateDatetimeEnd(DateUtil.getFrontDate(dateEnd, true));
        long a = jourDAO.selectTotalAmount(jour);
        return Math.abs(a);
    }

    @Override
    public Long getTotalAmount(String bizType, String accountNumber) {
        Jour jour = new Jour();
        jour.setBizType(bizType);
        jour.setAccountNumber(accountNumber);
        long a = jourDAO.selectTotalAmount(jour);
        return Math.abs(a);
    }

    @Override
    public List<Jour> queryDetailPage(int pageNO, int pageSize,
            Jour condition) {
        return jourDAO.selectJourDetailPage(pageNO, pageSize, condition);
    }

}
