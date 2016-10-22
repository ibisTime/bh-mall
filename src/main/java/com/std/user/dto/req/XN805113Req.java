package com.std.user.dto.req;

public class XN805113Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 等级名称（选填）
    private String name;

    // 作用（选填）
    private String effect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

}
