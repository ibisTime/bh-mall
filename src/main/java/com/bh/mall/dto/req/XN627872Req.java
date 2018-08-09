package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 导出防伪溯源码
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627872Req {

    // 每页数量
    @NotBlank(message = "每页数量不能为空")
    private String proNumber;

    // 张数
    @NotBlank(message = "张数不能为空")
    private String miniNumber;

    public String getProNumber() {
        return proNumber;
    }

    public String getMiniNumber() {
        return miniNumber;
    }

    public void setProNumber(String proNumber) {
        this.proNumber = proNumber;
    }

    public void setMiniNumber(String miniNumber) {
        this.miniNumber = miniNumber;
    }

}
