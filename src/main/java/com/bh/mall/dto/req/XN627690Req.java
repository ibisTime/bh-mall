package com.bh.mall.dto.req;

/**
 * 分页查售后
 * @author: nyc 
 * @since: 2018年3月29日 上午11:46:29 
 * @history:
 */

public class XN627690Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -4549169981177254437L;

    // （选填）申请人
    private String applyUser;

    // （选填）申请结束时间
    private String dateEnd;

    // （选填）申请结束时间
    private String dateStart;

    // （选填）状态
    private String status;

    // （选填）售后类型
    private String type;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
