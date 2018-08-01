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
 * 账户流水
 * @author: LENOVO 
 * @since: 2018年8月1日 上午9:29:58 
 * @history:
 */
public interface IJourAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 分页查询流水记录
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition);

    // 列表查询流水记录
    public List<Jour> queryJourList(Jour condition);

    // 获取详情
    public Jour getJour(String code);

    // 查询变动金额总和
    public Long getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd);

    // 分页查询流水记录（front）
    public Paginable<Jour> queryDetailPage(int start, int limit,
            Jour condition);

}
