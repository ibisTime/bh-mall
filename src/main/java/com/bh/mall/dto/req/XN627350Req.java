package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627350Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -6472775282840713162L;

    // （必填）用户名
    @NotBlank(message = "id不能为空")
    private String userId;

    // （选填）关键词
    private String keyWord;

    public String getUserId() {
        return userId;
    }

    public void setRealName(String userId) {
        this.userId = userId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
