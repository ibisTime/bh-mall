package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改介绍奖励
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:10:52 
 * @history:
 */
public class XN627241Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 奖励比例
    @NotBlank(message = "奖励比例不能为空")
    private String percent;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
