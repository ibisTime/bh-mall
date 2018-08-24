package com.bh.mall.dto.req;

/**
 * 我的出货
 * @author: nyc 
 * @since: 2018年6月30日 下午7:10:33 
 * @history:
 */
public class XN627852Req extends APageReq {

    private static final long serialVersionUID = -2391610940732263759L;

    // 推荐人
    private String referrer;

    // 介绍人
    private String introducer;

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

}
