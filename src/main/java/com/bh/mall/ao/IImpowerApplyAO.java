
package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.ImpowerApply;

@Component
public interface IImpowerApplyAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addImpowerApply(ImpowerApply data);

    // 查询授权申请 XN627288
    public Paginable<ImpowerApply> queryImpowerApplyPage(int start, int limit,
            ImpowerApply condition);

    // 列表查询轨迹 XN627289
    public List<ImpowerApply> queryImpowerApplyList(ImpowerApply condition);

    public ImpowerApply getImpowerApply(String code);

    // 分页查询轨迹 XN627290
    public Paginable<ImpowerApply> queryIntentionAgentFrontPage(int start,
            int limit, ImpowerApply condition);

}
