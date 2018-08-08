package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:41:26 
 * @history:
 */
public class XN627410Req extends APageReq {

    private static final long serialVersionUID = -475508549053098334L;

    // 用户编号
    @NotBlank
    private String userId;

    // 是否默认地址
    private String isDefault;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
