/**
 * @Title IAJourAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 上午9:29:01 
 * @version V1.0   
 */
package com.std.user.ao;

import com.std.user.annotation.ServiceModule;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.AccountJour;

/** 
 * @author: miyb 
 * @since: 2015-5-7 上午9:29:01 
 * @history:
 */
@ServiceModule
public interface IAJourAO {
    String DEFAULT_ORDER_COLUMN = "aj_no";

    /**
     * 分页获取
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-5-7 上午10:53:38 miyb
     * @history:
     */
    Paginable<AccountJour> queryAccountJourPage(int start, int limit,
            AccountJour condition);
}
