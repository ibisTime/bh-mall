
package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SqForm;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627362Req;
import com.bh.mall.dto.res.XN627303Res;

@Component
public interface ISqFormAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 代理申请，包含推荐人
    public XN627303Res applyHaveUserReferee(XN627251Req req);

    // 取消授权申请 XN626273
    public void cancelSqForm(String userId);

    public String addSqForm(SqForm data);

    // 审核授权
    public void approveSqForm(String userId, String approver, String result,
            String remark);

    // 审核取消授权
    public void approveCanenlSqForm(String userId, String approver,
            String remark);

    //  实名认证信息补充
    public void addInfo(XN627362Req req);

    // 查询授权申请 XN627288
    public Paginable<SqForm> querySqFormPage(int start, int limit,
            SqForm condition);

    // 列表查询轨迹 XN627289
    public List<SqForm> querySqFormList(SqForm condition);

    public SqForm getSqForm(String code);

    // 分页查询轨迹 XN627290
    public Paginable<SqForm> querySqFormFrontPage(int start, int limit,
            SqForm condition);

    // 审核取消授权
    public void approveCancelSqForm(String userId, String approver, String result,
            String remark);

}
