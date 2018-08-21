package com.bh.mall.dto.req;

import java.util.List;

/**
 * 内购订单分页
 * @author: nyc 
 * @since: 2018年3月27日 下午7:24:18 
 * @history:
 */
public class XN627731Req extends APageReq {

    private static final long serialVersionUID = 5483037686497515892L;

    // 订单状态
    private String status;

    // 所属团队
    private String teamName;

    // 下单人
    private String applyUser;

    // 订单状态
    private List<String> statusList;

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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
