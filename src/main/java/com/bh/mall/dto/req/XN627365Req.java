package com.bh.mall.dto.req;

/**
 * 分页查代理轨迹
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */
public class XN627365Req extends APageReq {

    private static final long serialVersionUID = 5058231875995341445L;

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

}
