package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.BUser;

public interface ISYSUserAO {

    String DEFAULT_ORDER_COLUMN = "user_id";

    // 注册
    // type

    // 用户登录 XN627300
    public String doLogin(String loginName, String loginPwd, String kind,
            String companyCode, String systemCode);

    public String addUser(String mobile, String loginPwd, String realName);

    // 注销 | 激活 XN627280
    public void doCloseOpen(String userId, String updater, String remark);

    // 检查登录密码
    public void doCheckLoginPwd(String userId, String password);

    // 重置登录密码 XN627304
    public void resetAdminLoginPwd(String userId, String newLoginPwd);

    // 修改照片 XN627279
    // public void doModifyPhoto(String userId, String photo);

    // 修改电话 XN627282
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha);

    /*************** 管理 *******************/

    // 更改信息

    // 代理分配 XN627270
    public void allotAgency(String userId, String toUserId, String manager,
            String approver);

    // 忽略意向 XN627269
    public void ignore(String userId, String updater);

    // 接受意向 XN627268
    // public void acceptIntention(String userId, String approver, String
    // remark);

    // 修改用户等级
    // public void doUpLevel(String userId, String level);

    // 修改上级 XN627261
    public void editManager(String userId, String manager, String updater);

    // 修改推荐人

    // 审核授权 XN627271
    public boolean approveImpower(String userId, String approver, String result,
            String remark);

    // 取消授权 XN627275
    public void approveImpowerCanenl(String userId, String approver,
            String result, String remark);

    // XN627276
    public void approveUplevelCanenl(String userId, String approver,
            String result, String remark);

    // 审核升级 XN627272
    public void approveUpgrade(String userId, String approver, String remark,
            String result);

    /*************** 查询 *******************/

    // 分页查询意向代理/OSS XN627354 & XN627361
    public Paginable<BUser> queryIntentionAgentPage(int start, int limit,
            BUser condition);

    // 代理结构OSS XN627352
    public List<BUser> queryAgentPage(BUser condition);

}
