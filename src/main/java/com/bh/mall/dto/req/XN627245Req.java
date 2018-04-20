package com.bh.mall.dto.req;

/**
 * 分页查询介绍奖励
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:33:35 
 * @history:
 */
public class XN627245Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -1602819369609298450L;

    // 等级
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
