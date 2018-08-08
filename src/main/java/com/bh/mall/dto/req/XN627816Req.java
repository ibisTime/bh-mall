package com.bh.mall.dto.req;

/**
 *  C端查询
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627816Req extends APageReq {

    private static final long serialVersionUID = -6966136403372983712L;

    // 用户编号（必填）
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
