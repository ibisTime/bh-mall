package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下架产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:02:17 
 * @history:
 */
public class XN627704Req {
    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）更新人
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
