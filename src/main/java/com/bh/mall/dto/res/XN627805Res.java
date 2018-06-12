package com.bh.mall.dto.res;

public class XN627805Res {

    // 结果
    private boolean result;

    // 红线金额
    private Long redAmount;

    // 账户余额
    private Long amount;

    public XN627805Res() {
    }

    public XN627805Res(boolean result, Long redAmount, Long amount) {
        super();
        this.result = result;
        this.redAmount = redAmount;
        this.amount = amount;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
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

}
