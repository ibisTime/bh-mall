package com.bh.mall.dto.req;

/**
 * 分页查询代理
 * @author nyc
 *
 */
public class XN627005Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3263503860757346138L;

    // 等级 （选填）
    private String level;

    // 等级 （选填）
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

}
