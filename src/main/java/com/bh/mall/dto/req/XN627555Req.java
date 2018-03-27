package com.bh.mall.dto.req;

/**
 * front分页查询
 * @author: nyc 
 * @since: 2018年3月25日 下午4:29:52 
 * @history:
 */
public class XN627555Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6673667078517042124L;

    // （选填）名称
    private String name;

    // （选填）用户ID
    private String userId;

    // （选填） 用户等级
    private String level;

    // （选填）规格状态
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
