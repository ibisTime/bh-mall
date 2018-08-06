package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除内购产品规格
 * @author: LENOVO 
 * @since: 2018年8月1日 下午9:54:16 
 * @history:
 */
public class XN627902Req {

    // 角色编号(必填)
    @NotBlank(message = "角色编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
