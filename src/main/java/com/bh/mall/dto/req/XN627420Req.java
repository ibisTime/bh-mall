package com.bh.mall.dto.req;

/**
 * 新增素材
 * @author: nyc 
 * @since: 2018年1月31日 下午5:10:05 
 * @history:
 */
public class XN627420Req {
    // （必填）类型
    private String type;

    // （必填）查看等级
    private String level;

    // （必填）标题
    private String title;

    // （必填）图片
    private String pic;

    // （必填）状态
    private String status;

    // （必填）排序
    private String orderNo;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getUpdater() {
        return updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
