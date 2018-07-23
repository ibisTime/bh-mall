package com.bh.mall.dto.res;

public class XN627805Res {

    // 结果
    private String result;

    // 补充云仓金额
    private Long redAmount;

    // 门槛最低余额
    private Long minAmount;

    // 授权或升级所购买
    private Long amount;

    // 等级
    private Integer level;

    // 门槛所需充值金额
    private Long chargeAmount;

    // 是否启用云仓
    private String isWareHouse;

    public XN627805Res() {
    }

    public XN627805Res(String result, Long redAmount, Long minAmount,
            Long amount, Long chargeAmount, Integer level, String isWareHouse) {
        super();
        this.result = result;
        this.redAmount = redAmount;
        this.minAmount = minAmount;
        this.amount = amount;
        this.chargeAmount = chargeAmount;
        this.level = level;
        this.isWareHouse = isWareHouse;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getRedAmount() {
        return redAmount;
    }

    public Long getAmount() {
        return minAmount;
    }

    public void setRedAmount(Long redAmount) {
        this.redAmount = redAmount;
    }

    public void setAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public Long getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Long chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIsWareHouse() {
        return isWareHouse;
    }

    public void setIsWareHouse(String isWareHouse) {
        this.isWareHouse = isWareHouse;
    }

}
