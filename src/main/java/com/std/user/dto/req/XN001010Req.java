package com.std.user.dto.req;

public class XN001010Req {
    // 系统编号(必填)
    private String systemCode;

    // 公司编号(必填)
    private String companyCode;

    // 登录名（必填）
    private String loginName;

    // 手机号（必填）
    private String mobile;

    // 更新人(必填)
    private String updater;

    // 证件类型（选填）
    private String idKind;

    // 证件号码（选填）
    private String idNo;

    // 真实姓名（选填）
    private String realName;

    // 推荐人（选填）
    private String userReferee;

    // 是否注册环信(选填)
    private String isRegHx;

    // 省(选填)
    private String province;

    // 市(选填)
    private String city;

    // 区(选填)
    private String area;

    // 备注(选填)
    private String remark;

}
