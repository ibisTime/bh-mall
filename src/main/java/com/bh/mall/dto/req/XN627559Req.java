package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据规格查详情
 * @author: nyc 
 * @since: 2018年3月25日 下午4:43:33 
 * @history:
 */
public class XN627559Req {

    // 规格编号
    @NotBlank(message = "规格编号不能为空")
    private String specsCode;

    // 等级
    @NotBlank(message = "等级不能为空")
    private String level;

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
