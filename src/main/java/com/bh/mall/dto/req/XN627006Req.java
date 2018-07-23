
package com.bh.mall.dto.req;

/**
 * 查询代理列表
 * @author: nyc 
 * @since: 2018年1月31日 下午2:53:07 
 * @history:
 */
public class XN627006Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 4088691140071594564L;

    // 等级名称 （选填）
    private String level;

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
}
