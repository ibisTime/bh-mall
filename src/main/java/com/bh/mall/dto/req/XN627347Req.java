package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627347Req extends APageReq {

    private static final long serialVersionUID = -8630781910648656197L;

    // 用户Id（必填）
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
