package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.Blacklist;

public interface IBlacklistAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    /**
     * 将某个用户拉入黑名单，一种类型只能拉黑一次
     * @param userId 用户编号
     * @param type 拉黑类型
     * @param systemCode 系统编号
     * @return 
     * @create: 2017年2月22日 下午12:22:04 haiqingzheng
     * @history:
     */
    public Long addBlacklist(String userId, String type, String remark,
            String systemCode);

    /**
     * 将某个已加入黑名单的用户解除
     * @param id
     * @param remark
     * @return 
     * @create: 2017年2月22日 下午1:15:18 haiqingzheng
     * @history:
     */
    public int dropBlacklist(Long id, String updater, String remark);

    /**
     * 查询某个用户是否拉入黑名单
     * @param userId
     * @param type
     * @param systemCode
     * @return 
     * @create: 2017年2月22日 下午2:51:14 haiqingzheng
     * @history:
     */
    public boolean isAdded(String userId, String type, String systemCode);

    public Paginable<Blacklist> queryBlacklistPage(int start, int limit,
            Blacklist condition);

    public List<Blacklist> queryBlacklistList(Blacklist condition);

    public Blacklist getBlacklist(Long id);

}
