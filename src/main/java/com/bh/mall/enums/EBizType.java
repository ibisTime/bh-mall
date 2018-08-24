package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

import com.bh.mall.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午2:15:22 
 * @history:
 */
public enum EBizType {
    AJ_REG("01", "注册送积分"), AJ_SIGN("02", "每日签到"), AJ_REG_REF("RF",
            "推荐注册送积分"), AJ_SCTX_FIRST("03",
                    "首次上传头像"), AJ_ZLWS_FIRST("04", "首次完善资料"),

    AJ_QX("11", "取现"), XXFK("XXFK", "提现回录"), AJ_CZ("AJ_CZ",
            "充值"), AJ_KK("AJ_KK", "扣款"), AJ_MKCZ("AJ_MKCZ", "门槛充值"),

    AJ_GMNP("AJ_GMNP", "购买内购产品"), AJ_GMYC("AJ_GMYC", "购买云仓"), AJ_CELR("AJ_CELR",
            "差额利润"), AJ_TJJL("AJ_TJJL", "推荐奖励"), AJ_CHJL("AJ_CHJL",
                    "出货奖励"), AJ_GMCP_TK("AJ_GMCP_TK", "购买产品退款"),

    AJ_QKYE("AJ_QKYE", "升级清空余额"), AJ_JSJL("AJ_JSJL", "介绍奖励"), AJ_YCCH("AJ_YCCH",
            "云仓出货"), AJ_YCZH("AJ_YCZH", "云仓置换"), AJ_GMCP("AJ_GMCP", "购买产品"),

    AJ_YCTH("AJ_YCTH", "云仓提货"), AJ_XGSJ("AJ_XGSJ", "修改上级"), AJ_QXSQ("AJ_QXSQ",
            "退出清空余额"), AJ_XGSH("AJ_XGSH", "更改上级"), AJ_XJCZ("AJ_XJCZ",
                    "下级微信充值"), AJ_XCX("AJ_XCX", "小程序付款"),

    AJ_CHJL_IN("AJ_CHJL_IN", "云仓订单出货奖"), AJ_TJJL_IN("AJ_TJJL_IN",
            "云仓订单推荐奖"), AJ_CHJL_OUT("AJ_CHJL_OUT",
                    "提货订单出货奖"), AJ_TJJL_OUT("AJ_TJJL_OUT", "提货订单推荐奖");

    public static EBizType getBizType(String code) {
        Map<String, EBizType> map = getBizTypeMap();
        EBizType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的bizType不存在");
        }
        return result;
    }

    public static Map<String, EBizType> getBizTypeMap() {
        Map<String, EBizType> map = new HashMap<String, EBizType>();
        for (EBizType bizType : EBizType.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EBizType(String code, String value) {
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
