package com.bh.mall.dto.req;

/**
 * 我的出货
 * @author: nyc 
 * @since: 2018年6月30日 下午7:10:33 
 * @history:
 */
public class XN627852Req extends APageReq {

    private static final long serialVersionUID = -2391610940732263759L;

    // 代理
    private String userId;

    // 推荐人
    private String referrer;

    // 介绍人
    private String introducer;

    // 关键查询
    private String keyword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReferrer() {
        return referrer;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
