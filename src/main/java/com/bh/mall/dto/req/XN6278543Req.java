package com.bh.mall.dto.req;

/**
 * 代理商个数
 * @author: nyc 
 * @since: 2018年6月30日 下午7:10:33 
 * @history:
 */
public class XN6278543Req extends APageReq {

    private static final long serialVersionUID = -2391610940732263759L;

    // 代理编号
    private String level;

    // 代理编号
    private String status;

    // 代理编号
    private String teamName;

    // 代理编号
    private String manager;

    // 省
    private String province;

    // 市区
    private String city;

    // 区(县)
    private String area;

    public String getLevel() {
        return level;
    }

    public String getStatus() {
        return status;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getManager() {
        return manager;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
