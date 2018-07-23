package com.bh.mall.ao;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.BUser;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.req.XN627362Req;
import com.bh.mall.dto.res.XN627303Res;

/**
 * B端代理用户
 * @author: clockorange 
 * @since: Jul 6, 2018 4:29:55 AM 
 * @history:
 */
public interface IBuserAO {

    String DEFAULT_ORDER_COLUMN = "user_id";

    // B端微信注册/登录 XN627278
    public XN627303Res doLoginWeChatByMerchant(String code, String userKind,
            String userReferee);

    // 设置角色 XN627311
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark);

    // 用户登录 XN627277
    public String doLogin(String loginName, String loginPwd, String kind);

    // 检查登录密码是否正确
    public void doCheckLoginPwd(String userId, String password);

    // 注销/激活用户 XN627303
    public void doCloseOpen(String userId, String updater, String remark);

    // 重置登录密码 XN627281
    public void resetLoginPwd(String userId, String newLoginPwd);

    // 修改头像 XN627308
    public void doModifyPhoto(String userId, String photo);

    // 更换手机号 XN627310
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha);

    // 更新信息 XN627255
    public void updateInformation(XN627255Req req);

    //  实名认证信息补充
    public void addInfo(XN627362Req req);

    /*************** 申请 *******************/

    // 代理申请
    public void applyIntent(XN627250Req req);

    // 代理申请，包含推荐人
    public XN627303Res applyHaveUserReferee(XN627251Req req);

    // 取消授权申请 XN626273
    public void cancelImpower(String userId);

    // 取消升级申请 XN627276
    public void cancelUplevel(String userId);

    // 升级申请 XN627267
    public void upgradeLevel(String userId, String highLevel, String payPdf,
            String teamName);

    // 接受意向 XN627268
    public void acceptIntention(String userId, String approver, String remark);

    /*************** 查询 *******************/

    // 我的下级 XN627350
    public Paginable<BUser> queryLowUser(int start, int limit, BUser condition);

    // 分页查询意向代理
    public Paginable<BUser> queryIntentionAgentPageFront(int start, int limit,
            BUser condition);

    // 代理结构 XN627351
    public Paginable<BUser> queryMyLowUserPage(int start, int limit,
            BUser condition);

    // 代理轨迹

    public BUser doGetUser(String userId);

    public BUser getUserName(String userReferee);

}
