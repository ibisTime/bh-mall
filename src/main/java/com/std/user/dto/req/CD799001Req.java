package com.std.user.dto.req;

public class CD799001Req {

    // 通道 -- 必填
    private String channel;

    // 接受短信的手机号--必填
    private String mobile;

    // 短信内容--必填
    private String content;

    // 指定发送时间 -- 选填
    private String sendDatetime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
    }
}
