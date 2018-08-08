package com.bh.mall.dto.req;

/**
 * 分页查售后
 * @author: nyc 
 * @since: 2018年3月29日 上午11:46:29 
 * @history:
 */

public class XN627690Req extends APageReq {

    private static final long serialVersionUID = -4549169981177254437L;

    // （选填）申请人
    private String keyword;

    // （选填）状态
    private String status;

    // （选填）售后类型
    private String type;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
