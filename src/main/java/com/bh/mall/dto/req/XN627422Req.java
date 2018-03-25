package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除素材
 * @author: nyc 
 * @since: 2018年2月1日 上午10:11:35 
 * @history:
 */
public class XN627422Req {

    // 编号 （必填）
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
