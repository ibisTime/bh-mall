package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 内购产品规格-详情
 * @author: LENOVO 
 * @since: 2018年8月2日 上午10:15:54 
 * @history:
 */
public class XN627907Req {

    // 规格编号(必填)
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
