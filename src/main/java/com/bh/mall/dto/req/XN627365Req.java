package com.bh.mall.dto.req;

/**
 * 分页查代理轨迹
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627365Req extends APageReq {

    private static final long serialVersionUID = 5058231875995341445L;

    // （选填）代理Id
    private String userId;

    // （选填）关键字
    private String keyword;

    // （选填）等级
    private String level;

    /// （选填）状态
    private String status;

    /// （选填）姓名
    private String realName;

    /// （选填）微信号
    private String wxId;

    /// （选填）团队名称
    private String teamName;

    /// （选填）手机号
    private String mobile;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public String getWxId() {
        return wxId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
