package com.std.user.dto.req;

public class CD799003Req {

    // 通道 --必填
    private String channel;

    // 接受短信的手机号--必填
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
