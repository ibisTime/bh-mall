/**
 * @Title IAccountJour.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:08:28 
 * @version V1.0   
 */
package com.std.user.dao;

import com.std.user.dao.base.IBaseDAO;
import com.std.user.domain.AccountJour;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:08:28 
 * @history:
 */
public interface IAJourDAO extends IBaseDAO<AccountJour> {
    String NAMESPACE = IAJourDAO.class.getName().concat(".");

    /** 
     * 对账结果录入
     * @param data
     * @return 
     * @create: 2015-2-23 下午4:28:41 miyb
     * @history: 
     */
    public int doCheckAccount(AccountJour data);
}
