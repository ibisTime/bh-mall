package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改推荐、出货奖
 * @author: nyc 
 * @since: 2018年3月26日 上午10:40:13 
 * @history:
 */
public class XN627580Req {
    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）直接推荐／出货奖励
    @NotBlank(message = "奖励不能为空")
    private String value1;

    // （选填）间接推荐奖励
    private String value2;

    // （选填）次推荐奖励
    private String value3;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

}
