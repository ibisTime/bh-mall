package com.bh.mall.dto.req;

/**
 * 代理结构
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627321Req extends APageReq {

    private static final long serialVersionUID = 9092885295612580388L;

    // （选填）备注
    private String keyword;

    // （选填） 用户Id
    private String userId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
