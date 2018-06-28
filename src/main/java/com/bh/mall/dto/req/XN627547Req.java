package com.bh.mall.dto.req;

/**
 * 规格定价
 * @author: nyc 
 * @since: 2018年3月23日 下午2:18:04 
 * @history:
 */
public class XN627547Req {

    // 编码
    private String code;

    // （必填）等级
    private String level;

    // （必填）价格
    private String price;

    // （必填）换货价
    private String changePrice;

    // （必填）是否可购买
    private String isBuy;

    // 最小数量
    private String minNumber;

    // 日限购
    private String dailyNumber;

    // 周限购
    private String weeklyNumber;

    // 月限购
    private String monthlyNumber;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(String isBuy) {
        this.isBuy = isBuy;
    }

    public String getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(String minNumber) {
        this.minNumber = minNumber;
    }

    public String getDailyNumber() {
        return dailyNumber;
    }

    public String getWeeklyNumber() {
        return weeklyNumber;
    }

    public String getMonthlyNumber() {
        return monthlyNumber;
    }

    public void setDailyNumber(String dailyNumber) {
        this.dailyNumber = dailyNumber;
    }

    public void setWeeklyNumber(String weeklyNumber) {
        this.weeklyNumber = weeklyNumber;
    }

    public void setMonthlyNumber(String monthlyNumber) {
        this.monthlyNumber = monthlyNumber;
    }

}
