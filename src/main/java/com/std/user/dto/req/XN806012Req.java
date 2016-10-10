package com.std.user.dto.req;

public class XN806012Req {

    // 省份（必填）
    private String province;

    // 城市（必填）
    private String city;

    // 区(县)（选填）
    private String area;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
