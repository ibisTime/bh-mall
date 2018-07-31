package com.bh.mall.ao;

import com.bh.mall.dto.res.XN627304Res;

/**
 * C端用户
 * @author: LENOVO 
 * @since: 2018年7月31日 下午9:17:11 
 * @history:
 */
public interface ICUserAO {

    // 登陆
    public String doLogin(String loginName, String loginPwd); // need?

    // 微信登录/注册
    public XN627304Res doLoginWeChatByCustomer(String code, String nickname,
            String avatarUrl, String kind);

    // 注销 / 激活用户
    public void doCloseOpen(String userId, String updater, String remark);

    // 修改照片
    public void doModifyPhoto(String userId, String photo);

    // 检查登录密码
    public void doCheckLoginPwd(String userId, String loginPwd);

    // 重置手机号
    public void doResetMoblie(String userId, String newMobile,
            String smsCaptcha);

}
