package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加库存
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:49 
 * @history:
 */
public class XN627920Req {
    // （必填）产品编号
    @NotBlank(message = "不能为空")
    private String code;

    // 类型 0 出库 1入库
    @NotBlank(message = "类型不能为空")
    private String type;

    // 变动数量
    @NotBlank(message = "变动数量不能为空")
    private String number;

    // （必填）更新人
    @NotBlank(message = "不能为空")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
