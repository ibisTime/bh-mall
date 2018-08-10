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

    String DEFAULT_ORDER_COLUMN = "user_id";

    // 代理申请
    public void applyYxForm(XN627250Req req);

    // 平台分配分配 627251
    public void allotYxFormByP(String userId, String toUserId, String approver,
            String remark);

    // 代理分配分配 627252
    public void allotYxFormByB(String userId, String toUserId, String approver,
            String remark);

    // 忽略意向 627255
    public void ignoreYxFormByP(String userId, String aprrover, String remark);

    // 忽略意向 627256
    public void ignoreYxFormByB(String userId, String aprrover, String remark);

    // 接受意向 627253
    public void acceptYxFormByP(String userId, String approver, String remark);

    // 接受意向 627254
    public void acceptYxFormByB(String userId, String approver, String remark);

    // 分页查询意 627265
    public Paginable<YxForm> queryYxFormPage(int start, int limit,
            YxForm condition);

    // 列表查询 627266
    public List<YxForm> queryYxFormList(YxForm condition);

    // 详细查询 627267
    public YxForm getYxForm(String userId);

    // 修改意向代理资料
    public void editYxForm(String userId, String wxId, String mobile,
            String realName, Integer integer, String province, String city,
            String area, String address);

}
