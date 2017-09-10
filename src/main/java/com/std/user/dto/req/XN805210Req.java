package com.std.user.dto.req;

public class XN805210Req {
    // 用户编号(必填)
    private String userId;

    // 学信网图片(必填)
    private String xuexinPic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getXuexinPic() {
        return xuexinPic;
    }

    public void setXuexinPic(String xuexinPic) {
        this.xuexinPic = xuexinPic;
    }
}
