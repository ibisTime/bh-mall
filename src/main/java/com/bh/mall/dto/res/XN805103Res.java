package com.bh.mall.dto.res;

public class XN805103Res {

    // 连续天数
    private Long days;

    // 今天是否签到
    private boolean todaySign;

    public XN805103Res() {

    }

    public XN805103Res(Long days, boolean todaySign) {
        this.days = days;
        this.todaySign = todaySign;
    }

    public XN805103Res(Long days) {
        this.days = days;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public boolean getTodaySign() {
        return todaySign;
    }

    public void setTodaySign(boolean todaySign) {
        this.todaySign = todaySign;
    }
}
