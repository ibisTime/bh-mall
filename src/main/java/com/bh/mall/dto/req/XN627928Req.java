package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 规格详情
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:49 
 * @history:
 */
public class XN627928Req {
    // （必填）产品编号
    @NotBlank(message = "不能为空")
    private String code;

    // 等级
    private String level;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
