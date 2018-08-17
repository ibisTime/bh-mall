
package com.bh.mall.dto.req;

/**
 * 查询代理列表
 * @author: nyc 
 * @since: 2018年1月31日 下午2:53:07 
 * @history:
 */
public class XN627006Req extends APageReq {

    private static final long serialVersionUID = 4088691140071594564L;

    // 等级 （选填）
    private String level;

    // 低于自己等级的等级（选填）
    private String lowLevel;

    // 高于自己等级的等级（选填）
    private String highLevel;

    // 等级名称 （选填）
    private String name;

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

    public String getLowLevel() {
        return lowLevel;
    }

    public String getHighLevel() {
        return highLevel;
    }

    public void setLowLevel(String lowLevel) {
        this.lowLevel = lowLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel;
    }

}
