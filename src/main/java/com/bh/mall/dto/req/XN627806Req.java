package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询余额红线
 * @author: nyc 
 * @since: 2018年4月23日 上午10:45:56 
 * @history:
 */
public class XN627806Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）数量
    @NotBlank(message = "数量不能为空")
    private String quantity;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （必填）备注
    @NotBlank(message = "更新人不能为空")
    private String remark;

    public String getCode() {
        return code;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUpdater() {
        return updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
