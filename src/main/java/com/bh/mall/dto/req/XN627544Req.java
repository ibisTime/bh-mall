package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下架产品
 * @author: nyc 
 * @since: 2018年3月25日 下午3:25:13 
 * @history:
 */
public class XN627544Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
