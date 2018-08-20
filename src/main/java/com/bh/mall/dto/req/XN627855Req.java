package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 我的出货
 * @author: nyc 
 * @since: 2018年6月30日 下午7:10:33 
 * @history:
 */
public class XN627855Req extends APageReq {

    private static final long serialVersionUID = -2391610940732263759L;

    // 代理编号
    @NotBlank(message = "代理编号不能为空")
    private String userId;

    // 流水类型
    private String bizType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

}
