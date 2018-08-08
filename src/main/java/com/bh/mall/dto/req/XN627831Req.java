package com.bh.mall.dto.req;

/**
 * 分页查流水
 * @author: nyc 
 * @since: 2018年4月10日 下午8:35:28 
 * @history:
 */
public class XN627831Req extends APageReq {

    private static final long serialVersionUID = 132773723046353379L;

    // （必填） 用户Id
    private String userId;

    // （选填）等级
    private String level;

    // （选填）产品名称
    private String keyword;

    // （选填）状态 0 申请置换 1 申请通过 2 申请未通过
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
