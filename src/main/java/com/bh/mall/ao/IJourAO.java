/**
 * @Title IJourAO.java 
 * @Package com.std.account.ao 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午9:05:07 
 * @version V1.0   
 */
package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Jour;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:05:07 
 * @history:
 */
public interface IJourAO {
    String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition);

    public List<Jour> queryJourList(Jour condition);

    public Jour getJour(String code);

    public Long getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd);

    public Paginable<Jour> queryDetailPage(int start, int limit,
            Jour condition);

}
