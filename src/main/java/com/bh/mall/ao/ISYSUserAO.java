package com.bh.mall.ao;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SYSUser;

public interface ISYSUserAO {

    String DEFAULT_ORDER_COLUMN = "user_id";

    // 用户登录 XN627300
    public String doLogin(String loginName, String loginPwd);

    public String addUser(String mobile, String loginPwd, String realName,
            String photo);

    // 注销 | 激活 XN627280
    public void doCloseOpen(String userId, String updater, String remark);

    public void doRoleUser(String userId, String roleCode, String updater,
            String remark);

    // 检查登录密码
    public void doCheckLoginPwd(String userId, String password);

    // 重置登录密码 XN627304
    public void resetAdminLoginPwd(String userId, String newLoginPwd);

    // 修改照片 XN627279
    // public void doModifyPhoto(String userId, String photo);

    // 修改电话 XN627282
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha);

    public void doModifyPhoto(String userId, String photo);

    /*************** 查询 *******************/

    public Paginable<SYSUser> queryUserPage(int start, int limit,
            SYSUser condition);

    // 登录密码
    public void resetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd);

}
