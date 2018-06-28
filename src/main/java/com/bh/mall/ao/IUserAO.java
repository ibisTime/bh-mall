package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.req.XN627362Req;
import com.bh.mall.dto.res.XN627302Res;

public interface IUserAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    // 用户登录
    public String doLogin(String loginName, String loginPwd, String kind,
            String companyCode, String systemCode);

    // B端微信注册/登录
    public XN627302Res doLoginWeChatByMerchant(String code, String userKind,
            String userReferee);

    // C端微信注册/登录
    public XN627302Res doLoginWeChatByCustomer(String code, String nickname,
            String photo, String kind);

    // 注销/激活用户
    public void doCloseOpen(String userId, String updater, String remark);

    // 设置角色
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark);

    // 重置登录密码
    public void resetAdminLoginPwd(String userId, String newLoginPwd);

    // 设置交易密码
    public void setTradePwd(String userId, String smsCaptcha, String tradePwd);

    // 修改头像
    public void doModifyPhoto(String userId, String photo);

    // 检查登录密码是否正确
    public void doCheckLoginPwd(String userId, String password);

    // 更换手机号
    void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha);

    // 修改用户等级
    public void doUpLevel(String userId, String level);

    public Paginable<User> queryUserPage(int start, int limit, User condition);

    public List<User> queryUserList(User condition);

    public User doGetUser(String userId);

    public List<User> getUserRefereeList(String userId);

    // 代理申请
    public void applyIntent(XN627250Req req);

    // 代理申请，包含推荐人
    public XN627302Res applyHaveUserReferee(XN627251Req req);

    // 代理分配
    public void allotAgency(String userId, String toUserId, String manager,
            String approver);

    // 意向通过
    public void acceptIntention(String userId, String approver, String remark);

    // 忽略意向
    public void ignore(String userId, String updater);

    // 修改信息
    void updateInformation(XN627255Req req);

    // 取消申请
    public void cancelImpower(String userId);

    // 审核授权
    public boolean approveImpower(String userId, String approver, String result,
            String remark);

    // 取消授权审核
    public void approveCanenl(String userId, String approver, String result,
            String remark);

    // 修改上级
    public void editHighUser(String userId, String highUser, String updater);

    // 修改推荐人
    public void editUserReferee(String userId, String userReferee,
            String updater);

    // 修改管理员
    public void editManager(String userId, String manager, String updater);

    // 升级申请
    public void upgradeLevel(String userId, String highLevel, String payPdf,
            String teamName);

    // 审核升级申请
    public void approveUpgrade(String userId, String approver, String remark,
            String result);

    // 我的下级
    public Paginable<User> queryLowUser(int start, int limit, User condition);

    // 代理结构
    public Paginable<User> queryMyLowUserPage(int start, int limit,
            User condition);

    // 代理结构OSS
    public List<User> queryAgentPage(User condition);

    // 分页查询意向代理
    public Paginable<User> queryIntentionAgentPageFront(int start, int limit,
            User condition);

    // 分页查询意向代理/OSS
    public Paginable<User> queryIntentionAgentPage(int start, int limit,
            User condition);

    // 代理轨迹
    public User getUserLog(int start, int limit, User condition);

    public User getUserName(String userReferee);

    public String addUser(String mobile, String loginPwd, String realName);

    // 补充授权所需信息
    public void addInfo(XN627362Req req);

    // 本等级是否实名
    public boolean isRealName(String userId);

    void addHighAccount(User user, Long amount);

    // 根据手机号获取用户详情
    public User doGetUserByMobile(String mobile);

}
