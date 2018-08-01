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

    // 意向分配
    public void allotYxForm(String userId, String toUserId, String approver);

    // 接受意向
    public void acceptYxForm(String userId, String approver, String remark);

    // 忽略意向
    public void ignore(String userId, String aprrover);

    // 列表查询
    public List<YxForm> queryYxFormList(YxForm condition);

    // 详细查询
    public YxForm getYxForm(String code);

    // 分页查询意
    public Paginable<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition);

}
