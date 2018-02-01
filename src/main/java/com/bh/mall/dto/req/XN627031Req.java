package com.bh.mall.dto.req;

/**
 * 修改素材
 * @author: nyc 
 * @since: 2018年2月1日 上午10:02:29 
 * @history:
 */
public class XN627031Req {

    // 编号 （必填）
    private String code;

    // 查看等级 （必填）
    private String levelList;

    // 标题 （必填）
    private String title;

    // 状态 （必填）
    private String status;

    // 排序 （必填）
    private String orderNo;

    // 图片 （必填）
    private String pic;

    // 类型 （必填）
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
