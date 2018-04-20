package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据等级产品详情查询
 * @author: nyc 
 * @since: 2018年3月25日 下午4:43:33 
 * @history:
 */
public class XN627558Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 等级
    @NotBlank(message = "等级不能为空")
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
