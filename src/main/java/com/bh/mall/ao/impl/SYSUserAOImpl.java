package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.ISYSRoleBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.common.PwdUtil;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class SYSUserAOImpl implements ISYSUserAO {

    // 平台端登录接口api
    private static Logger logger = Logger.getLogger(SYSUserAOImpl.class);

    @Autowired
    ISYSUserBO sysUserBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IBuserBO buserBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IOrderBO orderBO;

    @Autowired
    IInnerOrderBO innerOrderBO;

    @Autowired
    IAfterSaleBO afterSaleBO;

    @Autowired
    IYxFormBO agentAllotBO;

    @Autowired
    ISjFormBO sjFormBO;

    @Autowired
    ISqFormBO impowerApplyBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    /*************** 注册 **********************/
    @Override
    public String addUser(String mobile, String loginPwd, String realName,
            String photo) {
        sysUserBO.isMobileExist(mobile, ESystemCode.BH.getCode());
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setMobile(mobile);
        data.setRealName(realName);
        data.setLoginName(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setPhoto(photo);
        data.setStatus(EUserStatus.NORMAL.getCode());
        data.setCreateDatetime(new Date());
        data.setSystemCode(ESystemCode.BH.getCode());
        sysUserBO.doSaveUser(data);
        return userId;
    }

    /*************** 登录 **********************/
    // 用户登录
    @Override
    public String doLogin(String loginName, String loginPwd) {
        SYSUser condition = new SYSUser();

        condition.setLoginName(loginName);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        SYSUser user = userList2.get(0);

        return user.getUserId();
    }

    /*************** 注销 / 激活 **********************/

    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        SYSUser user = sysUserBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(user.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = EUserStatus.NORMAL;
        }
        sysUserBO.refreshStatus(userId, userStatus, updater, remark);
        if (PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile,
                "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "805091",
                user.getCompanyCode(), user.getSystemCode());
        }

    }

    /*************** 设置角色**********************/
    // 设置角色
    @Override
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark) {
        SYSUser user = sysUserBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        sysUserBO.refreshRole(userId, roleCode, updater, remark);
    }

    /*************** 修改密码 **********************/
    // 重置登录密码
    @Override
    public void resetAdminLoginPwd(String userId, String newLoginPwd) {
        SYSUser user = sysUserBO.getCheckUser(userId);
        sysUserBO.resetAdminLoginPwd(user, newLoginPwd);
    }

    /*************** 验证密码 **********************/
    // 验证登录密码
    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        SYSUser condition = new SYSUser();
        condition.setUserId(userId);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    /*************** 修改手机号 **********************/
    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha) {
        SYSUser user = sysUserBO.getCheckUser(userId);
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        sysUserBO.isMobileExist(newMobile, user.getSystemCode());
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "627282",
            user.getSystemCode());
        sysUserBO.resetBindMobile(user, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已将手机号码改为" + newMobile + "，您的登录名更改为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "631072", user.getCompanyCode(), user.getSystemCode());

    }

    /*************** 修改登录名 **********************/

    /*************** 修改照片 **********************/
    @Override
    public void doModifyPhoto(String userId, String photo) {
        sysUserBO.refreshPhoto(userId, photo);
    }

    /*************** 查询 **********************/
    @Override
    public Paginable<SYSUser> queryUserPage(int start, int limit,
            SYSUser condition) {
        /*
         * if (condition.getCreateDatetimeStart() != null &&
         * condition.getApplyDatetimeEnd() != null &&
         * condition.getApplyDatetimeStart()
         * .after(condition.getApplyDatetimeEnd())) { throw new
         * BizException("xn00000", "开始时间不能大于结束时间"); }
         */
        Paginable<SYSUser> page = sysUserBO.getPaginable(start, limit,
            condition);

        return page;
    }

    // 分页查询所有意向代理
    @Override
    public Paginable<BUser> queryIntentionAgentPage(int start, int limit,
            BUser condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<BUser> page = buserBO.getPaginable(start, limit, condition);
        BUser highUser = null;
        for (BUser data : page.getList()) {
            if (StringUtils.isNotBlank(data.getHighUserId())) {
                highUser = buserBO.getUser(data.getHighUserId());
                data.setHighUser(highUser);
            }
        }

        return page;
    }

    // 分页查询所有代理
    @Override
    public List<BUser> queryAgentPage(BUser condition) {
        if (StringUtils.isBlank(condition.getHighUserId())) {
            condition.setLevel(1);
        }
        List<BUser> list = buserBO.queryUserList(condition);
        if (StringUtils.isNotBlank(condition.getHighUserId())) {
            list = getAllLowUser(list);
        }
        return list;
    }

    // 下级
    public List<BUser> getAllLowUser(List<BUser> list) {
        for (int i = 0; i < list.size(); i++) {
            BUser buser = list.get(i);
            BUser condition = new BUser();
            condition.setHighUserId(buser.getUserId());
            List<BUser> userList = buserBO.queryUserList(condition);
            if (CollectionUtils.isNotEmpty(userList)) {
                buser.setUserList(userList);
                getAllLowUser(userList);
            }
        }
        return list;
    }

    /*************** 更改上级 **********************/
    @Override
    public void editManager(String userId, String manager, String updater) {
        BUser data = buserBO.getUser(userId);
        buserBO.getCheckUser(manager);
        buserBO.refreshManager(data, manager, updater);
    }

    private void changeHighUser(String highUserId, String userId,
            String approver, String remark) {

        List<BUser> list = buserBO.getUsersByUserReferee(userId);
        for (BUser buser : list) {
            Date date = new Date();
            buser.setHighUserId(userId);
            buser.setUpdater(approver);
            buser.setUpdateDatetime(date);

            buser.setRemark(remark);
            String logCode = buserBO.refreshHighUser(buser);
            buser.setLastAgentLog(logCode);
            buserBO.refreshHighUser(buser);

            List<BUser> list2 = buserBO
                .getUsersByUserReferee(buser.getUserId());
            for (BUser user2 : list2) {
                changeHighUser(highUserId, user2.getUserId(), approver, remark);
            }

        }

    }

    private void changeAmount(BUser data) {
        BUser highUser = buserBO.getUser(data.getUserId());
        // 推荐人的上级门槛转入新上级
        List<BUser> list = buserBO.getUsersByUserReferee(data.getUserId());
        BUser oldHighUser = null;
        for (BUser buser : list) {
            // 被推荐代理门槛款
            Account refreeAccount = accountBO.getAccountByUser(
                buser.getUserId(), ECurrency.MK_CNY.getCode());
            Account account = accountBO.getAccountByUser(highUser.getUserId(),
                ECurrency.MK_CNY.getCode());
            oldHighUser = buserBO.getCheckUser(buser.getUserId());

            // 旧代理上级是代理
            if (null == oldHighUser
                    || EUserKind.Plat.getCode().equals(oldHighUser.getKind())) {
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    refreeAccount.getAmount());
            }
            if (EUserKind.Merchant.getCode().equals(oldHighUser.getKind())) {
                String oldHighUserId = oldHighUser.getUserId();
                accountBO.transAmountCZB(oldHighUserId,
                    ECurrency.MK_CNY.getCode(), highUser.getUserId(),
                    ECurrency.MK_CNY.getCode(), refreeAccount.getAmount(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    EBizType.AJ_XGSH.getValue(), data.getUserId());
            }

            changeAmount(buser);

        }
    }

    // 分配账号 ----- buser
    private List<String> distributeAccount(String userId, String mobile,
            String kind, String companyCode, String systemCode) {
        List<String> currencyList = new ArrayList<String>();
        if (EUserKind.Customer.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
        } else if (EUserKind.Merchant.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
            currencyList.add(ECurrency.MK_CNY.getCode());
        }
        return currencyList;
    }

}
