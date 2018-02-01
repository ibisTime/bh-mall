package com.bh.mall.dto.req;

/**
 * 素材分页查询
 * @author: nyc 
 * @since: 2018年2月1日 上午11:04:25 
 * @history:
 */
public class XN627035Req extends APageReq {

    /** 
     * @Fields serialVersionUID : XXX(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -1370292147897079233L;

    // 类型（选填）
    private String type;

    // 标题 （选填）
    private String title;

    // 状态（选填）
    private String status;

    // 查看等级 （选填）
    private String levelList;

    public String getLevelList() {
        return levelList;
    }

    public void setLevelList(String levelList) {
        this.levelList = levelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
