package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.YxForm;
import com.bh.mall.dto.req.XN627250Req;

/**
 * 意向单
 * @author: clockorange 
 * @since: Jul 31, 2018 4:48:04 PM 
 * @history:
 */
public interface IYxFormAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 代理申请
    public void applyYxForm(XN627250Req req);

    // 平台分配分配
    public void allotYxFormByP(String userId, String toUserId, String approver,
            String remark);

    // 代理分配分配
    public void allotYxFormByB(String userId, String toUserId, String approver,
            String remark);

    // 忽略意向
    public void ignoreYxFormByP(String userId, String aprrover, String remark);

    // 忽略意向
    public void ignoreYxFormByB(String userId, String aprrover, String remark);

    // 接受意向
    public void acceptYxFormByP(String userId, String approver, String remark);

    // 接受意向
    public void acceptYxFormByB(String userId, String approver, String remark);

    // 列表查询
    public List<YxForm> queryYxFormList(YxForm condition);

    // 详细查询
    public YxForm getYxForm(String code);

    // 分页查询意
    public Paginable<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition);

}
