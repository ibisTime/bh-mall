package com.bh.mall.ao;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SignLog;
import com.bh.mall.dto.res.XN805100Res;
import com.bh.mall.dto.res.XN805103Res;

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
     * @param userId
     * @param location
     * @return 
     * @create: 2016年10月22日 上午12:14:56 xieyj
     * @history:
     */
    public XN805100Res addSignLog(String userId, String location);

    /** 
     * 分页查询签到纪录
     * @param condition
     * @return 
     * @create: 2016年9月18日 下午7:24:17 zuixian
     * @history: 
     */
    public Paginable<SignLog> querySignLogPage(SignLog condition, int start,
            int limit);

    /**
     * 获取连续签到天数
     * @param userId
     * @return 
     * @create: 2017年9月4日 下午8:36:58 xieyj
     * @history:
     */
    public XN805103Res getTodaySignDays(String userId);
}
