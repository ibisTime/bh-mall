package com.bh.mall.dto.req;

/**
 * 分页查询充值订单（front）
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:00:44 
 * @history:
 */
public class XN627473Req extends APageReq {

    private static final long serialVersionUID = 1678910941853269683L;

    // 用户ID （选填）
    private String userId;

    // 状态（待审核/审核不通过/审核通过）
    private String status;

    // 申请时间起
    private String applyDateStart;

    // 申请时间止
    private String applyDateEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(String applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public String getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(String applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

}
