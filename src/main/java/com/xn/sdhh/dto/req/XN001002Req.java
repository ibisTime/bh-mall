package com.xn.sdhh.dto.req;

/**
 * 查询用户是否在黑名单中
 * @author: xieyj 
 * @since: 2017年9月12日 下午3:31:18 
 * @history:
 */
public class XN001002Req {

    // 用户编号（必填）
    private String userId;

    // 类型（必填）
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
