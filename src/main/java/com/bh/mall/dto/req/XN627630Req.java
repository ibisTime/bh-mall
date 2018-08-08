package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询购物车产品
 * @author: nyc 
 * @since: 2018年3月27日 下午2:13:09 
 * @history:
 */
public class XN627630Req extends APageReq {

    private static final long serialVersionUID = 742369102842049069L;

    // 用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
