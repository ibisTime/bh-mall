package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.SignLog;

public interface ISignLogBO extends IPaginableBO<SignLog> {

    /** 
     * 签到
     * @param data
     * @return 
     * @create: 2016年9月18日 下午7:16:06 zuixian
     * @history: 
     */
    public int saveSignLog(SignLog data);

    /** 
     * 列表查询签到记录
     * @param condition
     * @return 
     * @create: 2016年9月18日 下午7:17:42 zuixian
     * @history: 
     */
    public List<SignLog> querySignLogList(SignLog condition);
}
