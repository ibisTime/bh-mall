package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * front分页查询
 * @author: nyc 
 * @since: 2018年3月25日 下午4:29:52 
 * @history:
 */
public class XN627555Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6673667078517042124L;

    // （选填）名称
    private String name;

    // （必填） 用户等级
    @NotBlank(message = "代理等级不能为空")
    private String level;

    // 状态
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
