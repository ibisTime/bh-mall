
package com.bh.mall.dto.req;

/**
 * 查询代理列表
 * @author: nyc 
 * @since: 2018年1月31日 下午2:53:07 
 * @history:
 */
public class XN627008Req {

    // 等级 （选填）
    private String level;

    // 等级名称 （选填）
    private String name;

    // 高于本等级的等级 （选填）
    private String highLevel;

    // 低于本等级的等级 （选填）
    private String lowLevel;

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

    public String getHighLevel() {
        return highLevel;
    }

    public String getLowLevel() {
        return lowLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel;
    }

    public void setLowLevel(String lowLevel) {
        this.lowLevel = lowLevel;
    }

}
