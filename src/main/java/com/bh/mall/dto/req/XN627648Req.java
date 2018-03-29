package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单作废
 * @author: nyc 
 * @since: 2018年3月28日 下午8:54:38 
 * @history:
 */

public class XN627648Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）作废原因
    @NotBlank(message = "原因不能为空")
    private String updateNote;

    // 必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateNote() {
        return updateNote;
    }

    public void setUpdateNote(String updateNote) {
        this.updateNote = updateNote;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
