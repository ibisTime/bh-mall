package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.YxForm;
import com.bh.mall.dto.req.XN627250Req;

public interface IYxFormAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 代理申请
    public void applyIntent(XN627250Req req);

    // 接受意向 XN627268
    public void acceptIntention(String userId, String approver, String remark);

    public void allotAgency(String userId, String toUserId, String approver);

    public void ignore(String userId, String aprrover);

    // 新增意向分配表
    public String addAgentAllot(YxForm data);

    // 修改意向分配表
    // 同意,忽略意向 改变意向归属人
    // public String updateAgentAllot(AgentAllot data);

    //  分页查询意向代理 XN627369
    public Paginable<YxForm> queryAgentAllotPage(int start, int limit,
            YxForm condition);

    // 列表查询轨迹 XN627360
    public List<YxForm> queryAgentAllotList(YxForm condition);

    public YxForm getAgentAllot(String code);

    // 分页查询轨迹 XN627353
    public Paginable<YxForm> queryIntentionAgentFrontPage(int start, int limit,
            YxForm condition);

}
