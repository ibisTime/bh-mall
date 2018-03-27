package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加库存
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:49 
 * @history:
 */
public class XN627545Req {
    // （必填）产品编号
    @NotBlank(message = "不能为空")
    private String code;

    // 类型 0 出库 1入库
    @NotBlank(message = "类型不能为空")
    private String type;

    // 种类Virtual("0", "虚拟"), Real("1", "真实");
    @NotBlank(message = "种类不能为空")
    private String kind;

    // 实际数量
    private String realNumber;

    // 虚拟数量
    private String virNumber;

    // （必填）更新人
    @NotBlank(message = "不能为空")
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRealNumber() {
        return realNumber;
    }

    public void setRealNumber(String realNumber) {
        this.realNumber = realNumber;
    }

    public String getVirNumber() {
        return virNumber;
    }

    public void setVirNumber(String virNumber) {
        this.virNumber = virNumber;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
