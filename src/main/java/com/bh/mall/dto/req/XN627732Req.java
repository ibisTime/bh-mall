package com.bh.mall.dto.req;

/**
 * 内购订单列表
 * @author: nyc 
 * @since: 2018年3月27日 下午7:24:18 
 * @history:
 */
public class XN627732Req {

    // 订单状态
    private String status;

    // 所属团队
    private String teamName;

    // 下单人
    private String applyUser;

    // 结束时间
    private String dateEnd;

    // 开始时间
    private String dateStart;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

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

}
