package com.std.user.ao;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.Blacklist;

public interface IBlacklistAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    // 将某个用户拉入黑名单，一种类型只能拉黑一次
    public void addBlacklist(String userId, String type, String remark);

    // 将某个已加入黑名单的用户解除
    public void dropBlacklist(Long id, String updater, String remark);

    // 查询是否黑名单
    public boolean isBlacklist(String userId, String type);

    public Paginable<Blacklist> queryBlacklistPage(int start, int limit,
            Blacklist condition);

    public Blacklist getBlacklist(Long id);

}
