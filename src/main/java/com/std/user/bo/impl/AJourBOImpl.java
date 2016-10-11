/**
 * @Title AJourBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:07 
 * @version V1.0   
 */
package com.std.user.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IAJourBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.common.DateUtil;
import com.std.user.dao.IAJourDAO;
import com.std.user.domain.AccountJour;
import com.std.user.enums.EAccountJourStatus;
import com.std.user.enums.EBoolean;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:07 
 * @history:
 */
@Component
public class AJourBOImpl extends PaginableBOImpl<AccountJour> implements
        IAJourBO {
    @Autowired
    private IAJourDAO aJourDAO;

    @Override
    public AccountJour getAccountJour(Long ajNo) {
        AccountJour data = null;
        if (ajNo > 0) {
            AccountJour condition = new AccountJour();
            condition.setAjNo(ajNo);
            data = aJourDAO.select(condition);
        }
        return data;
    }

    @Override
    public void doCheckAccount(Long aJNo, String checkUser, EBoolean checkResult) {
        if (aJNo > 0 && StringUtils.isNotBlank(checkUser)) {
            AccountJour data = new AccountJour();
            data.setAjNo(aJNo);
            data.setCheckUser(checkUser);
            data.setCheckDatetime(new Date());
            if (EBoolean.YES.getCode().equalsIgnoreCase(checkResult.getCode())) {
                data.setStatus(EAccountJourStatus.Checked_YES.getCode());
            } else {
                data.setStatus(EAccountJourStatus.Checked_NO.getCode());
            }
            aJourDAO.doCheckAccount(data);
        }
    }

    /** 
     * @see com.xnjr.moom.bo.IFDAJourBO#addJour(java.lang.String, java.lang.Long, java.lang.Long, com.xnjr.moom.enums.EBizType, java.lang.String)
     */
    @Override
    public void addJour(String accountNumber, Long preAmount, Long amount,
            String bizType, String refNo, String remark) {
        Long postAmount = preAmount + amount;
        AccountJour accountJour = new AccountJour();
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
        accountJour.setBizType(bizType);
        accountJour.setRefNo(refNo);

        accountJour.setTransAmount(amount);
        accountJour.setPreAmount(preAmount);
        accountJour.setPostAmount(postAmount);
        accountJour.setRemark(remark);
        accountJour.setCreateDatetime(new Date());
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));
        accountJour.setAccountNumber(accountNumber);
        aJourDAO.insert(accountJour);
    }

}
