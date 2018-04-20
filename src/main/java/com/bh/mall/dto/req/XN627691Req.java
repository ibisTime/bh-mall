package com.bh.mall.dto.req;

/**
 * 列表查售后
 * @author: nyc 
 * @since: 2018年3月29日 上午11:46:29 
 * @history:
 */
public class XN627691Req {

    // （选填）申请人
    private String keyword;

    // （选填）申请结束时间
    private String dateEnd;

    // （选填）申请结束时间
    private String dateStart;

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

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
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
