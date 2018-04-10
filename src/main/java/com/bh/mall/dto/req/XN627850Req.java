package com.bh.mall.dto.req;

/**
 * 分页查询云仓产品
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627850Req {

    // 背景图 （必填）
    private String bg_pic;

    // 授权编号
    private String code;

    // 授权截止时间
    private String end_datetime;

    // 身份证号
    private String id_number;

    // 加入时间
    private String jion_datetime;

    // 授权等级
    private String level;

    // 授权人姓名
    private String name;

    // 图片
    private String pic;

    // 授权开始时间
    private String start_datetime;

    // 微信号
    private String wx_id;

    public String getBg_pic() {
        return bg_pic;
    }

    public void setBg_pic(String bg_pic) {
        this.bg_pic = bg_pic;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getJion_datetime() {
        return jion_datetime;
    }

    public void setJion_datetime(String jion_datetime) {
        this.jion_datetime = jion_datetime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(String start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getWx_id() {
        return wx_id;
    }

    public void setWx_id(String wx_id) {
        this.wx_id = wx_id;
    }

}
