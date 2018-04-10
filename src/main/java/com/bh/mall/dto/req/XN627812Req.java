package com.bh.mall.dto.req;

/**
 * 分页查询云仓产品
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627812Req {

    // 关键字（选填）
    private String keyword;

    // 用户编号（选填）
    private String type;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
