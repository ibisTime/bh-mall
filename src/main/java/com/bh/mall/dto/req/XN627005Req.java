package com.bh.mall.dto.req;

/**
 * 分页查询代理
 * @author: nyc 
 * @since: 2018年1月31日 下午2:40:21 
 * @history:
 */
public class XN627005Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3263503860757346138L;

    // 等级 （选填）
    private String level;

    // 低于自己等级的等级（选填）
    private String lowLevel;

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

    public void setLowLevel(String lowLevel) {
        this.lowLevel = lowLevel;
    }

}
