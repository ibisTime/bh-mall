package com.std.user.dto.req;

public class XN805120Req {

    // 类型(必填)(1 公告 2 新闻)
    private String type;

    // 标题(必填)
    private String title;

    // 内容(必填)
    private String content;

    // 作用等级(必填)
    private String toLevel;

    // 更新人(必填)
    private String updater;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToLevel() {
        return toLevel;
    }

    public void setToLevel(String toLevel) {
        this.toLevel = toLevel;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
