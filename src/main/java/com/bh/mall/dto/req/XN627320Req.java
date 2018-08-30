package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 我的下级
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627320Req extends APageReq {

    private static final long serialVersionUID = 9092885295612580388L;

    // 用户id
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // （选填）用户名
    private String realName;

    // 等级
    private String level;

    // （选填）备注
    private String keyword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

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

}
