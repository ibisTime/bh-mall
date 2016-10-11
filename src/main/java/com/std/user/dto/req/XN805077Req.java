package com.std.user.dto.req;

/**
 * @author: xieyj 
 * @since: 2016年10月12日 上午6:38:45 
 * @history:
 */
public class XN805077Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 登录名(选填)
    private String loginName;

    // 昵称(选填)
    private String nickname;

    // 用户等级(选填)
    private String level;

    // 手机号(选填)
    private String mobile;

    // 管理端用户编号(必填)
    private String ossUserId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOssUserId() {
        return ossUserId;
    }

    public void setOssUserId(String ossUserId) {
        this.ossUserId = ossUserId;
    }
}
