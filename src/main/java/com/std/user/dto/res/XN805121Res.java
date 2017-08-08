/**
 * @Title XN805121Res.java 
 * @Package com.std.user.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月8日 下午2:53:50 
 * @version V1.0   
 */
package com.std.user.dto.res;

import java.util.Date;

import com.std.user.domain.User;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月8日 下午2:53:50 
 * @history:
 */
public class XN805121Res {
    // userId
    private String userId;

    // 登陆名
    private String loginName;

    // 手机号
    private String mobile;

    // 头像
    private String photo;

    // 昵称
    private String nickname;

    // 登陆密码
    private String loginPwd;

    // 登陆密码强度
    private String loginPwdStrength;

    // 身份标识
    private String kind;

    // 用户等级
    private String level;

    // 推荐人
    private String userReferee;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 真实姓名
    private String realName;

    // 支付密码
    private String tradePwd;

    // 支付密码强度
    private String tradePwdStrength;

    // 角色编号
    private String roleCode;

    // 分成比例
    private Double divRate;

    // 状态
    private String status;

    // 开放平台和公众平台唯一号
    private String unionId;

    // 微信h5第三方登录开放编号
    private String h5OpenId;

    // 微信app第三方登录开放编号
    private String appOpenId;

    // 性别(1 男 0 女)
    private String gender;

    // 介绍
    private String introduce;

    // 生日
    private Date birthday;

    // 邮箱
    private String email;

    // 学位
    private String diploma;

    // 职业
    private String occupation;

    // 工作年限
    private String workTime;

    // 附件
    private String pdf;

    // 省
    private String province;

    // 市区
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

    // 经度
    private String longitude;

    // 维度
    private String latitude;

    // 注册时间
    private Date createDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 所属公司
    private String companyCode;

    // 系统编号
    private String systemCode;

    /***** 辅助字段 ******/

    // 是否设置交易密码
    private boolean tradepwdFlag;

    // 关注数
    private Long totalFollowNum;

    // 粉丝数
    private Long totalFansNum;

    // 用户推荐人
    private User refereeUser;
}
