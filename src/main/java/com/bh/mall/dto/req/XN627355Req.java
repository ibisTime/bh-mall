package com.bh.mall.dto.req;

/**
 * 分页查意向代理
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627355Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 9092885295612580388L;

    // （选填）搜索关键字;（姓名、微信号等）
    private String keyword;

    // （选填）等级
    private String level;

    // (选填)上级代理
    private String highLevel;

    // （选填）状态
    private String status;

    // （选填）团队名称
    private String teamName;

    // （选填）推荐人
    private String userReferee;

    // 用户类型 （选填）
    private String kind;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
