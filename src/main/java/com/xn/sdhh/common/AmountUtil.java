package com.xn.sdhh.common;

import java.math.BigDecimal;

public class AmountUtil {
    public static Long mul(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b).longValue();
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiUp(Long amount) {
        String amountString = CalculationUtil.diviUp(amount);
        return Long.valueOf(CalculationUtil.multUp(amountString));
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiDown(Long amount) {
        String amountString = CalculationUtil.diviDown(amount);
        return Long.valueOf(CalculationUtil.multDown(amountString));
    }

    public static void main(String[] args) {
        System.out.println(mul(100L, 2.0));
    }
}
