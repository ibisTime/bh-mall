package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.CUser;
import com.bh.mall.enums.EUserStatus;

public interface ICUserBO extends IPaginableBO<CUser> {

    //
    public CUser doGetUserByOpenId(String h5OpenId);

    // 查询openId
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId);

    // 前端用户注册

    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String loginPwd, String nickname, String photo,
            String status);

    public String doRegister(String wxId, String unionId, String h5OpenId,
            String appOpenId, String mobile, String kind, String loginPwd,
            String nickname, String photo);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId);

    // 验证支付密码:拿tradePwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkTradePwd(String userId, String tradePwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd, String alertStr);

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile);

    // 判断登录名是否存在
    public void isLoginNameExist(String loginName);

    public String getUserId(String mobile);

    public List<CUser> queryUserList(String mobile);

    public List<CUser> queryUserList(CUser condition);

    public CUser getUser(String userId);

    public CUser getCheckUser(String userId);

    public void refreshNickname(String userId, String nickname);

    public void refreshUser(CUser data);

    public void refreshWxInfo(String userId, String unionId, String h5OpenId,
            String appOpenId, String nickname, String photo);

    //
    public void refreshPhoto(String userId, String photo);

    public String saveUser(String mobile);

    public CUser getUserNoCheck(String userId);

    public CUser getUserByLoginName(String loginName);

    public void refreshStatus(String userId, EUserStatus status);

}
