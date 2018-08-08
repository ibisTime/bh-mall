package com.bh.mall.dto.req;

/**
 * 分页出货奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:34:58 
 * @history:
 */
public class XN627865Req extends APageReq {

    private static final long serialVersionUID = -1192585662108393493L;

    // （必选填） 等级
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
