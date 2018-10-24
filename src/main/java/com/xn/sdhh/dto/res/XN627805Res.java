package com.xn.sdhh.dto.res;

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
    private String isWare;

    public XN627805Res() {
    }

    public XN627805Res(String result, Long redAmount, Long minAmount,
            Long amount, Long chargeAmount, Integer level, String isWare) {
        super();
        this.result = result;
        this.redAmount = redAmount;
        this.minAmount = minAmount;
        this.amount = amount;
        this.chargeAmount = chargeAmount;
        this.level = level;
        this.isWare = isWare;
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
        return amount;
    }

    public void setRedAmount(Long redAmount) {
        this.redAmount = redAmount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public String getIsWare() {
        return isWare;
    }

    public void setIsWare(String isWare) {
        this.isWare = isWare;
    }

}
