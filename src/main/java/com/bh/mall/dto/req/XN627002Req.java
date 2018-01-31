package com.bh.mall.dto.req;

/**
 * 修改代理
 * @author nyc
 *
 */
public class XN627002Req {

    // 编码 （必填）
    private String code;

    // 等级 （必填）
    private String level;

    // 等级名称 （必填）
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
