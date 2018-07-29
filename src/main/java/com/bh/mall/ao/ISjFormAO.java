package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SjForm;

public interface ISjFormAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addUplevelApply(SjForm data);

    // 取消升级申请 XN627276
    public void cancelUplevel(String userId);

    // 升级申请 XN627267
    public void upgradeLevel(String userId, String highLevel, String payPdf,
            String teamName);

    // 查询升级申请 XN627291
    public Paginable<SjForm> queryUplevelApplyPage(int start, int limit,
            SjForm condition);

    // 列表查询轨迹 XN627292
    public List<SjForm> queryUplevelApplyList(SjForm condition);

    public SjForm getUplevelApply(String code);

    // 分页查询轨迹 XN627293
    public Paginable<SjForm> queryIntentionAgentFrontPage(int start, int limit,
            SjForm condition);

    public void approveUpgrade(String userId, String approver, String remark);

    public void approveUplevelCancel(String userId, String approver,
            String result, String remark);

}
