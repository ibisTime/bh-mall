package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 代理升级设置
 * @author: nyc 
 * @since: 2018年8月27日 下午2:22:22 
 * @history:
 */
public class XN627002Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）等级
    @NotBlank(message = "等级不能为空")
    private String level;

    // （必填）等级名称
    @NotBlank(message = "等级不能为空")
    private String name;

    // （必填）本等级升级是否公司审核
    @NotBlank(message = "等级不能为空")
    private String isCompanyApprove;

    // （必填）半门槛推荐人数
    @NotBlank(message = "等级不能为空")
    private String reNumber;

    // （必填）升级门槛是否清零
    @NotBlank(message = "等级不能为空")
    private String isReset;

    // （必填）升级门槛是否清零
    @NotBlank(message = "等级不能为空")
    private String amount;

    // （必填）更新人
    @NotBlank(message = " 更新人不能为空")
    private String updater;

    // （选填） 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsCompanyApprove() {
        return isCompanyApprove;
    }

    public void setIsCompanyApprove(String isCompanyApprove) {
        this.isCompanyApprove = isCompanyApprove;
    }

    public String getReNumber() {
        return reNumber;
    }

    public void setReNumber(String reNumber) {
        this.reNumber = reNumber;
    }

    public String getIsReset() {
        return isReset;
    }

    public void setIsReset(String isReset) {
        this.isReset = isReset;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
