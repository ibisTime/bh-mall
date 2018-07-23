package com.bh.mall.ao;

import com.bh.mall.dto.res.XN627304Res;

public interface ICUserAO {

    public String doLogin(String loginName, String loginPwd); // need?

    // 微信登录/注册 XN627302
    public XN627304Res doLoginWeChatByCustomer(String code, String nickname,
            String avatarUrl, String kind);

    // 注销 / 激活用户 XN627284
    public void doCloseOpen(String userId, String updater, String remark);

    // 设置交易密码 XN627306
    public void setTradePwd(String userId, String smsCaptcha, String tradePwd);

    // 修改照片 XN627286
    public void doModifyPhoto(String userId, String photo);

    // 检查登录密码 XN627287
    public void doCheckLoginPwd(String userId, String loginPwd);

    // 重置手机号
    public void doResetMoblie(String userId, String newMobile,
            String smsCaptcha);

    // rest password？

}
