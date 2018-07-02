package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 导出防伪溯源码
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627870Req {

    // 导出数量
    @NotBlank(message = "盒码数量不能为空")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
