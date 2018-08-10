package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SqForm;
import com.bh.mall.dto.req.XN627270Req;
import com.bh.mall.dto.req.XN627271Req;

/**
 * 授权单
 * @author: clockorange 
 * @since: Jul 31, 2018 5:29:27 PM 
 * @history:
 */
@Component
public interface ISqFormAO {

    static final String DEFAULT_ORDER_COLUMN = "user_id";

    // 代理申请，包含推荐人
    public void applyHaveUserReferee(XN627270Req req);

    //  授权所需信息补充
    public void addInfo(XN627271Req req);

    // 审核授权 627272
    public void approveSqFormByP(String userId, String manager, String approver,
            String result, String remark);

    // 审核授权 627273
    public void approveSqFormByB(String userId, String approver, String result,
            String remark);

    // 申请退出 627274
    public void toQuit(String userId);

    // 审核取消授权(代理) 627276
    public void cancelSqFormByB(String userId, String approver, String result,
            String remark);

    // 审核取消授权(平台) 627275
    public void cancelSqFormByP(String userId, String approver, String result,
            String remark);

    // 分页查询 627285
    public Paginable<SqForm> querySqFormPage(int start, int limit,
            SqForm condition);

    // 列表查询 627286
    public List<SqForm> querySqFormList(SqForm condition);

    // 详细查询 627287
    public SqForm getSqForm(String code);

}
