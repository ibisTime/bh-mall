package com.bh.mall.dto.req;

/**
 * 素材详情查询
 * @author: nyc 
 * @since: 2018年2月1日 上午11:53:49 
 * @history:
 */
public class XN627036Req {

    // TODO
    // 类型 （选填）
    private String type;

    // 标题 （选填）
    private String title;

    // 状态 （选填）
    private String status;

    // 查看等级（选填）
    private String levelList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLevelList() {
        return levelList;
    }

    public void setLevelList(String levelList) {
        this.levelList = levelList;
    }

}
