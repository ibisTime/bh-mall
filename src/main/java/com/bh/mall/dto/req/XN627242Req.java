package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除介绍奖励
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:10:52 
 * @history:
 */
public class XN627242Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
