/**
 * @Title IAJourBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:19:46 
 * @version V1.0   
 */
package com.std.user.bo;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.AccountJour;
import com.std.user.enums.EBoolean;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:19:46 
 * @history:
 */
public interface IAJourBO extends IPaginableBO<AccountJour> {
    /**
     * 获取单条账户流水记录
     * @param ajNo
     * @return 
     * @create: 2015-5-5 上午11:21:13 miyb
     * @history:
     */
    public AccountJour getAccountJour(Long ajNo);

    /**
     * 对账结果录入
     * @param aJNo
     * @param checkUser
     * @param checkResult
     * @return 
     * @create: 2016年1月15日 下午2:44:53 myb858
     * @history:
     */
    public void doCheckAccount(Long aJNo, String checkUser, EBoolean checkResult);

    /**
     * 新增流水
     * @param accountNumber
     * @param preAmount
     * @param amount
     * @param bizType
     * @param refNo
     * @param remark
     * @param systemCode 
     * @create: 2016年12月23日 上午8:11:33 xieyj
     * @history:
     */
    public void addJour(String accountNumber, Long preAmount, Long amount,
            String bizType, String refNo, String remark, String systemCode);

}
