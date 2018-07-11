package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询防伪码
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627880Req {

    // 防伪码
    @NotBlank(message = "防伪码不能为空")
    private String securityCode;

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

}
