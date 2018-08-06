package com.bh.mall.dto.req;

public class XN627306Req extends APageReq {

    private static final long serialVersionUID = 33243741736735919L;

    // （选填）关键字
    private String keyword;

    // （选填）等级
    private String level;

    // （选填）等级
    private String applyLevel;

    /// （选填）状态
    private String status;

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

    public String getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(String applyLevel) {
        this.applyLevel = applyLevel;
    }

}
