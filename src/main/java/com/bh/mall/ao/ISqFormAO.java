
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
    public void cancelImpower(String userId);

    public String addImpowerApply(SqForm data);

    public void approveImpowerCanenl(String userId, String approver,
            String remark);

    public boolean approveImpower(String userId, String approver,
            String remark);

    //  实名认证信息补充
    public void addInfo(XN627362Req req);

    // 查询授权申请 XN627288
    public Paginable<SqForm> queryImpowerApplyPage(int start, int limit,
            SqForm condition);

    // 列表查询轨迹 XN627289
    public List<SqForm> queryImpowerApplyList(SqForm condition);

    public SqForm getImpowerApply(String code);

    // 分页查询轨迹 XN627290
    public Paginable<SqForm> queryIntentionAgentFrontPage(int start, int limit,
            SqForm condition);

}
