package com.bh.mall.dto.req;

/**
 * 分页查代理轨迹
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627360Req {
    // （选填）审核开始时间
    private String dateStart;

    // （选填）审核结束时间
    private String dateEnd;

    // （选填）关键字
    private String keyword;

    // （选填）等级
    private String level;

    /// （选填）状态
    private String status;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

}
