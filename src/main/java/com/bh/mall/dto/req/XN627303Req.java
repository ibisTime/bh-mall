package com.bh.mall.dto.req;

/**
 * 注销/激活用户
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:10:36 
 * @history:
 */
public class XN627303Req {
    // 用户编号
    private String userId;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
