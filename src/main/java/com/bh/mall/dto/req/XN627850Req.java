package com.bh.mall.dto.req;

/**
 * 我的出货
 * @author: nyc 
 * @since: 2018年6月30日 下午7:10:33 
 * @history:
 */
public class XN627850Req extends APageReq {

    private static final long serialVersionUID = -2391610940732263759L;

    // 代理编号
    private String userId;

    // 代理编号
    private String realName;

    // 等级
    private String level;

    // 手机号
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLevel() {
        return level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
