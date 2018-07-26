package com.bh.mall.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {
    BankCard("BC", "银行卡"), Withdraw("W", "取现"), Account("A", "账户"), AJour("AJ",
            "流水"), DH("DH", "导航"), YQ("YQ", "邀请"), AD("AD", "用户地址"),

    MATERIAL("MA", "素材编码"), PRODUCT("P", "产品编码"), ProductSpecs("PS",
            "产品规格编码"), ProductSpecsPrice("PSP",
                    "规格定价编号"), ProductLog("PL", "产品库存记录"),

    Award("AW", "奖励编号"), InnerProduct("IP", "内购产品编号"), Cart("CA",
            "购物车编号"), InnerOrder("IN",
                    "内购产品订单编号"), Order("OA", "产品订单"), AfterSale("AS", "售后单编号"),

    Charge("CO", "充值订单"), AgencyLog("AL", "代理编号"), Intro("IT",
            "介绍奖励"), WareHouse("WH", "云仓库编号"), WareHourseLog("WHL",
                    "云仓库记录编号"), WareHourseSpecs("WHP", "云仓产品规格"),

    ChangeProduct("CP", "置换单编号"), Report("R", "统计表编号"), IntroLevel("IL",
            "可介绍等级"), AwardInterval("AI", "出货奖区间"), proCode("BC", "条形码表");

    public static Map<String, EGeneratePrefix> getOrderTypeMap() {
        Map<String, EGeneratePrefix> map = new HashMap<String, EGeneratePrefix>();
        for (EGeneratePrefix orderType : EGeneratePrefix.values()) {
            map.put(orderType.getCode(), orderType);
        }
        return map;
    }

    EGeneratePrefix(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
