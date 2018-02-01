package com.bh.mall.dto.req;

/**
 * 新增素材
 * @author: nyc 
 * @since: 2018年1月31日 下午5:10:05 
 * @history:
 */
public class XN627030Req {

    // 类型（必填）
    private String type;

    // 标题（必填）
    private String title;

    // 图片（必填）
    private String pic;

    // 排序（必填）
    private String orderNo;

    // 状态（必填 0 未发布， 1发布）
    private String status;

    // 查看等级（必填）
    private String levelList;

    public String getLevelList() {
        return levelList;
    }

    public void setLevelList(String levelList) {
        this.levelList = levelList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
