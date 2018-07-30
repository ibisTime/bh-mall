package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SjForm;

public interface ISjFormAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSjForm(SjForm data);

    // 取消升级申请 XN627276
    public void cancelSjForm(String userId);

    // 升级申请 XN627267
    public void applySjForm(String userId, String highLevel, String payPdf,
            String teamName);

    // 查询升级申请 XN627291
    public Paginable<SjForm> querySjFormPage(int start, int limit,
            SjForm condition);

    // 列表查询轨迹 XN627292
    public List<SjForm> querySjFormList(SjForm condition);

    public SjForm getSjForm(String code);

    // 分页查询轨迹 XN627293
    public Paginable<SjForm> queryISjFormFrontPage(int start, int limit,
            SjForm condition);

    // 审核升级
    public void approveSjForm(String userId, String approver, String result,
            String remark);

    public void approveCancelSjForm(String userId, String approver,
            String result, String remark);

}
