package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.UpLevelApply;

public interface IUpLevelApplyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addUplevelApply(UpLevelApply data);

    // 查询升级申请 XN627291
    public Paginable<UpLevelApply> queryUplevelApplyPage(int start, int limit,
            UpLevelApply condition);

    // 列表查询轨迹 XN627292
    public List<UpLevelApply> queryUplevelApplyList(UpLevelApply condition);

    public UpLevelApply getUplevelApply(String code);

    // 分页查询轨迹 XN627293
    public Paginable<UpLevelApply> queryIntentionAgentFrontPage(int start,
            int limit, UpLevelApply condition);

}
