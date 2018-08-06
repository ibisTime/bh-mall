package com.bh.mall.dto.req;

/**
 * 分页查询意向代理
 * @author: nyc 
 * @since: 2018年8月5日 下午10:07:46 
 * @history:
 */
public class XN627265Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -1814685367618601346L;

    // （选填） 申请等级
    private String applyLevel;

    // （选填） 搜索关键字
    private String keyword;

    // （选填） 状态
    private String status;

    // （必填） 用户ID
    private String toUserId;

    // （选填） 审核人
    private String approver;

    public String getApplyLevel() {
        return applyLevel;
    }

    public String getApprover() {
        return approver;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getStatus() {
        return status;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setApplyLevel(String applyLevel) {
        this.applyLevel = applyLevel;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
}
