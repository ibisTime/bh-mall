package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.SignLog;

/** 
 * 签到AO
 * @author: zuixian 
 * @since: 2016年9月18日 下午7:23:49 
 * @history:
 */
public interface ISignLogAO {

    String DEFAULT_ORDER_COLUMN = "order_no";

    /** 
     * 签到
     * @param data
     * @return 
     * @create: 2016年9月18日 下午7:23:58 zuixian
     * @history: 
     */
    public String saveSignLog(String userId, String location);

    /** 
     * 列表查询签到记录
     * @param condition
     * @return 
     * @create: 2016年9月18日 下午7:24:05 zuixian
     * @history: 
     */
    public List<SignLog> querySignLogList(String userId,
            String signDatetimeStart, String signDatetimeEnd);

    /** 
     * 分页查询签到纪录
     * @param condition
     * @return 
     * @create: 2016年9月18日 下午7:24:17 zuixian
     * @history: 
     */
    public Paginable<SignLog> querySignLogPage(SignLog condition, int start,
            int limit);
}
