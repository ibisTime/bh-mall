/**
 * @Title UserAOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:52:06 
 * @version V1.0   
 */
package com.std.user.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.user.ao.IUserAO;
import com.std.user.bo.IAJourBO;
import com.std.user.bo.IAccountBO;
import com.std.user.bo.IBankCardBO;
import com.std.user.bo.ICompanyBO;
import com.std.user.bo.IFieldTimesBO;
import com.std.user.bo.IIdentifyBO;
import com.std.user.bo.IRuleBO;
import com.std.user.bo.ISYSRoleBO;
import com.std.user.bo.ISignLogBO;
import com.std.user.bo.ISmsOutBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.IUserExtBO;
import com.std.user.bo.IUserRelationBO;
import com.std.user.bo.base.Paginable;
import com.std.user.common.DateUtil;
import com.std.user.common.MD5Util;
import com.std.user.common.PhoneUtil;
import com.std.user.common.PropertiesUtil;
import com.std.user.domain.Company;
import com.std.user.domain.SYSRole;
import com.std.user.domain.User;
import com.std.user.domain.UserExt;
import com.std.user.domain.UserRelation;
import com.std.user.dto.res.XN805154Res;
import com.std.user.dto.res.XN805155Res;
import com.std.user.enums.EAccountType;
import com.std.user.enums.EBizType;
import com.std.user.enums.EBoolean;
import com.std.user.enums.ECurrency;
import com.std.user.enums.EDirection;
import com.std.user.enums.EFieldType;
import com.std.user.enums.EIDKind;
import com.std.user.enums.ELoginType;
import com.std.user.enums.EPrefixCode;
import com.std.user.enums.ERuleKind;
import com.std.user.enums.ERuleType;
import com.std.user.enums.ESystemCode;
import com.std.user.enums.EUser;
import com.std.user.enums.EUserKind;
import com.std.user.enums.EUserLevel;
import com.std.user.enums.EUserPwd;
import com.std.user.enums.EUserStatus;
import com.std.user.exception.BizException;
import com.std.user.third.hx.impl.InstantMsgImpl;
import com.std.user.util.RandomUtil;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:52:06 
 * @history:
 */
@Service
public class UserAOImpl implements IUserAO {
    @Autowired
    protected IUserBO userBO;

    @Autowired
    protected InstantMsgImpl instantMsgImpl;

    @Autowired
    protected IUserRelationBO userRelationBO;

    @Autowired
    protected IUserExtBO userExtBO;

    @Autowired
    protected ISYSRoleBO sysRoleBO;

    @Autowired
    protected IAccountBO accountBO;

    @Autowired
    IIdentifyBO dentifyBO;

    @Autowired
    protected IBankCardBO bankCardBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    protected IAJourBO aJourBO;

    @Autowired
    protected ICompanyBO companyBO;

    @Autowired
    protected IRuleBO ruleBO;

    @Autowired
    protected IFieldTimesBO fieldTimesBO;

    @Autowired
    protected ISignLogBO signLogBO;

    @Override
    public void doCheckMobile(String mobile, String kind, String systemCode) {
        userBO.isMobileExist(mobile, kind, systemCode);
    }

    /** 
     * @see com.std.user.ao.IUserAO#doCheckMobile(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckMobile(String mobile, String kind, String companyCode,
            String systemCode) {
        userBO.isMobileExist(mobile, kind, companyCode, systemCode);
    }

    @Override
    @Transactional
    public String doRegister(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String kind, String isRegHx, String systemCode) {
        // 验证手机号
        userBO.isMobileExist(mobile, kind, systemCode);
        // 验证推荐人是否是平台的已注册用户,将userReferee手机号转化为用户编号
        if (StringUtils.isNotBlank(userReferee)) {
            User refereeUser = userBO.getUserByMobileAndKind(userReferee, kind,
                systemCode);
            if (null == refereeUser) {
                throw new BizException("xn702002", "推荐人不存在");
            }
            userReferee = refereeUser.getUserId();
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805041");
        // 插入用户信息
        String userId = userBO.doRegister(mobile, null, mobile, loginPwd,
            loginPwdStrength, userReferee, kind, EUserLevel.ZERO.getCode(), 0L,
            null, null, null, systemCode);
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        if (EBoolean.YES.getCode().equals(isRegHx)) {
            // 注册环信
            instantMsgImpl.doRegisterUser(userId, EUserPwd.InitPwd.getCode(),
                systemCode);
        }
        // 分配账号(人民币和虚拟币)
        if (ESystemCode.ZH_QB.getCode().equals(systemCode)) {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.GXB.getCode());
            currencyList.add(ECurrency.QBB.getCode());
            currencyList.add(ECurrency.GWB.getCode());
            currencyList.add(ECurrency.HBB.getCode());
            currencyList.add(ECurrency.HBYJ.getCode());
            accountBO.distributeAccountList(userId, mobile,
                getAccountType(kind), currencyList, systemCode);
        } else {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.XNB.getCode());
            accountBO.distributeAccountList(userId, mobile,
                getAccountType(kind), currencyList, systemCode);
        }
        return userId;
    }

    @Override
    @Transactional
    public XN805154Res doRegisterAddJf(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String systemCode) {
        // 验证手机号
        userBO.isMobileExist(mobile, systemCode);
        // 验证推荐人是否是平台的已注册用户
        userBO.checkUserReferee(userReferee, systemCode);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805154");
        // 插入用户信息
        String kind = EUserKind.F1.getCode();
        String userId = userBO.doRegister(mobile, null, mobile, loginPwd,
            loginPwdStrength, userReferee, kind, "0", 0L, null, null, null,
            systemCode);
        // 分配账号(人民币和虚拟币)
        accountBO.distributeAccount(userId, mobile, getAccountType(kind),
            ECurrency.CNY.getCode(), systemCode);
        // XN802001Res accountRes = accountBO.distributeAccountTwo(userId,
        // mobile,
        // ECurrency.XNB.getCode(), userReferee);
        // 设置用户关系
        if (StringUtils.isNotBlank(userReferee)) {
            userRelationBO.saveUserRelation(userReferee, userId, systemCode);
        }
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        // return new XN805154Res(userId, accountRes.getAmount());
        return new XN805154Res(userId, "0");
    }

    @Override
    @Transactional
    public String doRegisterSingle(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String companyCode, String isMall, String systemCode) {
        // 验证手机号
        if (StringUtils.isNotBlank(isMall)
                && EBoolean.YES.getCode().equals(isMall)) {
            userBO.isMobileExist(mobile, null, companyCode);
        } else {
            userBO.isMobileExist(mobile, systemCode);
        }
        // 验证推荐人是否是平台的已注册用户
        userBO.checkUserReferee(userReferee, systemCode);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805076");
        // 设置公司
        Company company = companyBO.getCompany(companyCode);
        if (company == null) {
            Company result = companyBO.getDefaultCompany(systemCode);
            if (result != null) {
                companyCode = result.getCode();
            }
        }
        // 注册送钱
        Long amount = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.ZC,
            EBoolean.NO.getCode());
        // 插入用户信息
        String userId = userBO.doRegister(EPrefixCode.CSW.getCode() + mobile,
            null, mobile, loginPwd, loginPwdStrength, userReferee,
            EUserKind.F1.getCode(), null, amount, companyCode, null, null,
            systemCode);
        if (amount != null && amount > 0) {
            aJourBO.addJour(userId, 0L, amount, EBizType.AJ_SR.getCode(), null,
                ERuleType.ZC.getValue(), systemCode);
        }
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        return userId;
    }

    @Override
    @Transactional
    public String doThirdRegister(String openId, String nickname, String photo,
            String gender, String companyCode, String systemCode) {
        User condition = new User();
        condition.setOpenId(openId);
        long count = userBO.getTotalCount(condition);
        if (count > 0) {
            throw new BizException("xn702002", "第三方开放编号已存在");
        }
        // 设置公司
        Company company = companyBO.getCompany(companyCode);
        if (company == null) {
            Company result = companyBO.getDefaultCompany(systemCode);
            if (result != null) {
                companyCode = result.getCode();
            }
        }
        // 注册送钱
        Long amount = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.ZC,
            EBoolean.NO.getCode());
        // 插入用户信息
        String loginPwd = EUserPwd.InitPwd.getCode();
        String userId = userBO.doRegister(openId, nickname, null, loginPwd,
            "1", null, EUserKind.F1.getCode(), "0", amount, companyCode,
            openId, null, systemCode);
        if (amount != null && amount > 0) {
            aJourBO.addJour(userId, 0L, amount, EBizType.AJ_SR.getCode(), null,
                ERuleType.ZC.getValue(), systemCode);
        }
        // 新增扩展信息
        userExtBO.saveUserExt(userId, photo, gender, systemCode);
        return userId;
    }

    @Override
    @Transactional
    public String doAddUser(String loginName, String mobile, String idKind,
            String idNo, String realName, String userReferee, String updater,
            String remark, String kind, String pdf, String roleCode,
            String systemCode) {
        String userId = null;
        // 插入用户信息
        String loginPsd = EUserPwd.InitPwd.getCode();
        if (EUserKind.F1.getCode().equals(kind)
                || EUserKind.F2.getCode().equals(kind)) {
            // 验证手机号
            userBO.isMobileExist(mobile, kind, systemCode);
            // 插入用户信息
            loginPsd = RandomUtil.generate6();
            String tradePsd = EUserPwd.InitPwd.getCode();
            userId = userBO.doAddUser(mobile, mobile, loginPsd, userReferee,
                realName, idKind, idNo, tradePsd, kind, "0", remark, updater,
                pdf, null, systemCode);
            // 三方认证
            // dentifyBO.doIdentify(userId, realName, idKind, idNo);
            // 分配账号(人民币和虚拟币)
            accountBO.distributeAccount(userId, realName,
                this.getAccountType(kind), ECurrency.CNY.getCode(), systemCode);
            accountBO.distributeAccount(userId, realName,
                this.getAccountType(kind), ECurrency.XNB.getCode(), systemCode);
            if (EUserKind.F1.getCode().equals(kind)) {
                userRelationBO
                    .saveUserRelation(userReferee, userId, systemCode);
                // 新增扩展信息
                userExtBO.saveUserExt(userId, systemCode);
            }
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您已成功注册。您的登录密码为" + loginPsd
                    + "，请及时登录网站修改密码。如有疑问，请联系客服："
                    + PropertiesUtil.Config.COMPANY_MOBILE + "。", "805042");
        } else if (EUserKind.Operator.getCode().equals(kind)) {
            // 验证登录名
            userBO.isLoginNameExist(loginName, null, systemCode);
            // 插入用户信息
            userId = userBO.doAddUser(loginName, mobile, loginPsd, userReferee,
                realName, idKind, idNo, loginPsd, kind, "0", remark, updater,
                pdf, roleCode, systemCode);
        } else if (EUserKind.Integral.getCode().equals(kind)
                || EUserKind.Goods.getCode().equals(kind)
                || EUserKind.CaiGo.getCode().equals(kind)
                || EUserKind.Merchant.getCode().equals(kind)) {
            // 验证登录名
            userBO.isLoginNameExist(loginName, null, systemCode);
            int level = 1;
            if (StringUtils.isNotBlank(userReferee)) {
                String preUserId = userReferee;
                while (true) {
                    User data = userBO.getUser(preUserId);
                    if (data != null) {
                        preUserId = data.getUserReferee();
                        level++;
                        // 超过3级，按3级处理
                        if (level > 3) {
                            level = 3;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }

            String cxRoleCode = null;
            if (EUserKind.Integral.getCode().equals(kind)) {
                cxRoleCode = PropertiesUtil.Config.NOTOP_JFROLECODE;
            } else if (EUserKind.Goods.getCode().equals(kind)) {
                cxRoleCode = PropertiesUtil.Config.NOTOP_HPJFROLECODE;
            } else if (EUserKind.CaiGo.getCode().equals(kind)) {
                cxRoleCode = PropertiesUtil.Config.NOTOP_HPJFROLECODE;
            } else if (EUserKind.Merchant.getCode().equals(kind)) {
                cxRoleCode = PropertiesUtil.Config.SJROLECODE;
            }
            // 插入用户信息
            userId = userBO.doAddUser(loginName, mobile, loginPsd, userReferee,
                realName, idKind, idNo, loginPsd, kind, level + "", remark,
                updater, pdf, cxRoleCode, systemCode);
            // 分配人民币账号
            accountBO.distributeAccount(userId, realName, null,
                ECurrency.CNY.getCode(), systemCode);
            // 分配积分账号
            accountBO.distributeAccount(userId, realName, null,
                ECurrency.XNB.getCode(), systemCode);
        }
        return userId;
    }

    @Override
    @Transactional
    public String doAddUser(String mobile, String realName, String userReferee,
            String updater, String remark, String kind, String systemCode) {
        // 验证手机号
        userBO.isMobileExist(mobile, kind, systemCode);
        // 插入用户信息
        String loginPsd = RandomUtil.generate6();
        String tradePsd = RandomUtil.generate6();
        String userId = userBO.doAddUser(null, mobile, loginPsd, null,
            realName, null, null, tradePsd, kind, "0", remark, updater, null,
            null, systemCode);
        // 分配账号
        accountBO.distributeAccount(userId, realName, getAccountType(kind),
            ECurrency.CNY.getCode(), systemCode);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您已成功注册。您的登录密码为" + loginPsd + ";交易密码为" + tradePsd
                + "，请及时登录个金所网站修改密码。如有疑问，请联系客服：400-0008-139。", "805042");
        return userId;
    }

    /** 
     * @see com.std.user.ao.IUserAO#doAddUser(java.lang.String, java.lang.String)
     */
    @Override
    public String doAddUser(String mobile, String companyCode,
            String userReferee, String systemCode) {
        // 验证手机号
        userBO.isMobileExist(mobile, systemCode);
        companyBO.isCompanyExist(companyCode);
        // 注册送钱
        Long amount = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.ZC,
            EBoolean.NO.getCode());
        // 插入用户信息
        String loginPwd = RandomUtil.generate6();
        String userId = userBO.doRegister(EPrefixCode.CSW.getCode() + mobile,
            null, mobile, loginPwd, "1", userReferee, EUserKind.F1.getCode(),
            EUserLevel.ZERO.getCode(), amount, companyCode, null, null,
            systemCode);
        if (amount != null && amount > 0) {
            aJourBO.addJour(userId, 0L, amount, EBizType.AJ_SR.getCode(), null,
                ERuleType.ZC.getValue(), systemCode);
        }
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，恭喜您成功注册。初始化密码为" + loginPwd + "，请及时登录网站更改密码。", "805079");
        return userId;
    }

    @Override
    public String doLogin(String loginName, String loginPwd, String kind,
            String companyCode, String systemCode) {
        User condition = new User();
        if (EUserKind.F1.getCode().equals(kind)
                || EUserKind.F2.getCode().equals(kind)) {
            condition.setLoginName(loginName);
            condition.setLoginType(ELoginType.ALL.getCode());
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
        } else {
            condition.setLoginName(loginName);
        }
        condition.setSystemCode(systemCode);
        List<User> userList1 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }
        User user = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn702002", "当前用户已被锁定，请联系工作人员");
        }
        return user.getUserId();
    }

    @Override
    @Transactional
    public XN805155Res doLoginAddJf(String loginName, String loginPwd,
            String kind, String systemCode) {
        User condition = new User();
        if (EUserKind.F1.getCode().equals(kind)) {
            condition.setLoginName(loginName);
            condition.setLoginType(ELoginType.ALL.getCode());
            condition.setKind(kind);
        } else {
            condition.setLoginName(loginName);
        }
        List<User> userList1 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }
        User user = userList2.get(0);

        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn702002", "当前用户已被锁定，请联系工作人员");
        }
        // 签到日志
        String userId = user.getUserId();
        String amount = null;
        // 判断是否已经签到
        Boolean result = signLogBO.isSignToday(userId);
        if (!result) {
            signLogBO.saveSignLog(userId, "", systemCode);
            // 加积分
            // XN802317Res res = accountBO.loginAddJf(userId);
            amount = "0";// res.getAmount();
        }
        return new XN805155Res(userId, amount);
    }

    @Override
    public void doIdentify(String userId, String idKind, String idNo,
            String realName) {
        // 三方认证
        dentifyBO.doIdentify(userId, realName, idKind, idNo);
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // 回写Account表realName;
        // accountBO.refreshRealName(userId, realName);
    }

    @Override
    public void doEditRealName(String userId, String realName) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn702002", "用户不存在");
        }
        userBO.refreshRealName(userId, realName);
    }

    @Override
    @Transactional
    public void doSetTradePwd(String userId, String tradePwd,
            String tradePwdStrength, String smsCaptcha) {
        // 判断是否和登录密码重复
        User user = this.doGetUser(userId);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805045");
        userBO.refreshTradePwd(userId, tradePwd, tradePwdStrength);
        // 发送短信
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的交易密码设置成功。请妥善保管您的账户相关信息。", "805045");
    }

    @Override
    @Transactional
    public void doIdentifySetTradePwd(String userId, String idKind,
            String idNo, String realName, String tradePwd,
            String tradePwdStrength, String smsCaptcha) {
        // 三方认证
        dentifyBO.doIdentify(userId, realName, idKind, idNo);
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // 回写Account表realName;
        accountBO.refreshRealName(userId, realName);

        // 开始交易密码设置
        // 判断是否和登录密码重复
        User user = this.doGetUser(userId);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805046");
        userBO.refreshTradePwd(userId, tradePwd, tradePwdStrength);
        // 发送短信
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您已通过实名认证，且交易密码设置成功。请妥善保管您的账户相关信息。", "805046");
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd, String isMall) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 验证手机号
        if (StringUtils.isNotBlank(isMall)
                && EBoolean.YES.getCode().equals(isMall)) {
            userBO.isMobileExist(newMobile, user.getKind(),
                user.getCompanyCode());
        } else {
            userBO.isMobileExist(newMobile, user.getSystemCode());
        }
        // 验证交易密码
        userBO.checkTradePwd(userId, tradePwd);
        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805047");
        userBO.refreshMobile(userId, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(
            oldMobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(oldMobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "提交的更改绑定手机号码服务已审核通过，现绑定手机号码为" + newMobile
                    + "，请妥善保管您的账户相关信息。", "805047");
    }

    @Override
    public void doBindMoblie(String userId, String mobile, String smsCaptcha,
            String companyCode) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            throw new BizException("li01004", "手机号已经绑定，无需再次操作");
        }
        // 验证手机号
        userBO.isMobileExist(mobile, null, companyCode);
        // 短信验证码是否正确（往手机号发送）
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805153");
        // 插入用户信息
        String loginPwd = RandomUtil.generate6();
        userBO.refreshBindMobile(userId, EPrefixCode.CSW.getCode() + mobile,
            mobile, loginPwd, "1");
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，登录密码为" + loginPwd + "，请及时登录网站更改密码。", "805153");
    }

    @Override
    @Transactional
    public void doFindLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd, String loginPwdStrength, String kind,
            String systemCode) {
        User user = userBO.getUserByMobileAndKind(mobile, kind, systemCode);
        if (user == null) {// 这里其实还有一种处理方法：就是直接注册
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805048");
        userBO.refreshLoginPwd(user.getUserId(), MD5Util.md5(newLoginPwd),
            loginPwdStrength);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码找回成功。请妥善保管您的账户相关信息。", "805048");
    }

    @Override
    @Transactional
    public void doFindLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd, String loginPwdStrength, String kind,
            String companyCode, String systemCode) {
        User user = userBO.getUserByMobileAndKind(mobile, kind, companyCode,
            systemCode);
        if (user == null) {// 这里其实还有一种处理方法：就是直接注册
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805171");
        userBO.refreshLoginPwd(user.getUserId(), MD5Util.md5(newLoginPwd),
            loginPwdStrength);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码找回成功。请妥善保管您的账户相关信息。", "805171");
    }

    @Override
    public void doSendLoginPwdSms(String loginName, String systemCode) {
        User condition = new User();
        condition.setLoginName(loginName);
        condition.setSystemCode(systemCode);
        List<User> userList = userBO.queryUserList(condition);
        User data = null;
        if (CollectionUtils.isEmpty(userList)) {
            throw new BizException("xn702001", "登录名不存在");
        } else {
            data = userList.get(0);
        }
        smsOutBO.sendCaptcha(data.getMobile(), "805059",
            EUserKind.Operator.getCode(), systemCode);
    }

    @Override
    @Transactional
    public void doFindLoginPwdByOss(String loginName, String smsCaptcha,
            String newLoginPwd, String loginPwdStrength, String systemCode) {
        User user = userBO.getUserByLoginName(loginName, systemCode);
        if (user == null) {
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        String mobile = user.getMobile();
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805059");
        userBO.refreshLoginPwd(user.getUserId(), MD5Util.md5(newLoginPwd),
            loginPwdStrength);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码找回成功。请妥善保管您的账户相关信息。", "805059");
    }

    @Override
    @Transactional
    public void doFindLoginPwdByOss(String userId, String adminUserId,
            String adminPwd) {
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(adminUserId, adminPwd, "管理员密码");
        userBO.refreshLoginPwd(userId, MD5Util.md5(EUserPwd.InitPwd.getCode()),
            EBoolean.YES.getCode());
    }

    @Override
    @Transactional
    public void doResetLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd, String loginPwdStrength) {
        if (oldLoginPwd.equals(newLoginPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(userId, oldLoginPwd);
        // 重置
        userBO.refreshLoginPwd(userId, MD5Util.md5(newLoginPwd),
            loginPwdStrength);
        // 发送短信
        User user = userBO.getUser(userId);
        String mobile = user.getMobile();
        String userKind = user.getKind();
        if (EUserKind.F1.getCode().equals(userKind)
                || EUserKind.F2.getCode().equals(userKind)) {
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的登录密码修改成功。请妥善保管您的账户相关信息。", "805049");
        }
    }

    @Override
    @Transactional
    public void doFindTradePwd(String userId, String newTradePwd,
            String tradePwdStrength, String smsCaptcha, String idKind,
            String idNo) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户名不存在");
        }
        if (user.getIdKind() == null || user.getIdNo() == null) {
            throw new BizException("li01004", "请先实名认证");
        }
        // 证件是否正确
        if (!(user.getIdKind().equalsIgnoreCase(idKind) && user.getIdNo()
            .equalsIgnoreCase(idNo))) {
            throw new BizException("li01009", "证件验证不通过");
        }
        // 短信验证码是否正确
        String mobile = user.getMobile();
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805050");
        userBO.refreshTradePwd(userId, newTradePwd, tradePwdStrength);
        String userKind = user.getKind();
        if (EUserKind.F1.getCode().equals(userKind)
                || EUserKind.F2.getCode().equals(userKind)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的交易密码找回成功。请妥善保管您的账户相关信息。", "805050");
        }
    }

    @Override
    public void doFindTradePwd(String userId, String newTradePwd,
            String tradePwdStrength, String smsCaptcha) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li010004", "用户名不存在");
        }
        // 短信验证码是否正确
        String mobile = user.getMobile();
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805057");
        userBO.refreshTradePwd(userId, newTradePwd, tradePwdStrength);
        // 发送短信
        String userKind = user.getKind();
        if (EUserKind.F1.getCode().equals(userKind)
                || EUserKind.F2.getCode().equals(userKind)) {
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的交易密码找回成功。请妥善保管您的账户相关信息。", "805057");
        }
    }

    @Override
    @Transactional
    public void doResetTradePwd(String userId, String oldTradePwd,
            String newTradePwd, String tradePwdStrength) {
        if (oldTradePwd.equals(newTradePwd)) {
            throw new BizException("li01008", "新交易密码与原有交易密码重复");
        }
        User conditon = new User();
        conditon.setUserId(userId);
        conditon.setTradePwd(MD5Util.md5(oldTradePwd));
        List<User> list = userBO.queryUserList(conditon);
        User user = null;
        if (CollectionUtils.isNotEmpty(list)) {
            user = list.get(0);
        } else {
            throw new BizException("li01008", "旧交易密码不正确");
        }
        userBO.refreshTradePwd(userId, newTradePwd, tradePwdStrength);
        // 发送短信
        String userKind = user.getKind();
        if (EUserKind.F1.getCode().equals(userKind)
                || EUserKind.F2.getCode().equals(userKind)) {
            String mobile = user.getMobile();
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的交易密码修改成功。请妥善保管您的账户相关信息。", "805051");
        }
    }

    @Override
    public void doStatusUser(String userId, String toStatus, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户名不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.Ren_Locked.getCode().equalsIgnoreCase(toStatus)) {
            smsContent = "用户，您已经被注销。";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "用户，您已经被激活。";
            userStatus = EUserStatus.NORMAL;
        }
        userBO.refreshStatus(userId, userStatus, updater, remark);
        if (!EUserKind.Operator.getCode().equals(user.getKind())) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + smsContent, "805052");
        }
    }

    @Override
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        if (!user.getSystemCode().equals(role.getSystemCode())) {
            throw new BizException("li01004", "用户和角色系统不对应");
        }
        userBO.refreshRole(userId, roleCode, updater, remark);
    }

    @Override
    public void doPdfUser(String userId, String pdf, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户名不存在");
        }
        userBO.refreshPdf(userId, pdf, updater, remark);
    }

    @Override
    public Paginable<User> queryUserPage(int start, int limit, User condition) {
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        List<User> list = page.getList();
        for (User user : list) {
            UserExt userExt = userExtBO.getUserExt(user.getUserId());
            user.setUserExt(userExt);
            if (EBoolean.YES.getCode().equals(condition.getIsGetAmount())) {
                // XN802013Res res =
                // accountBO.getAccountDetail(user.getUserId(),
                // ECurrency.XNB.getCode());
                // if (res != null) {
                // user.setAmount(res.getAmount());
                // } else {
                // user.setAmount(0L);
                // }
            }
        }
        return page;
    }

    @Override
    public List<User> queryUserList(User condition) {
        List<User> list = userBO.queryUserList(condition);
        for (User user : list) {
            UserExt userExt = userExtBO.getUserExt(user.getUserId());
            user.setUserExt(userExt);
        }
        return list;
    }

    @Override
    public List<User> getUserRefereeList(String userId) {
        List<User> list = new ArrayList<User>();
        User user = userBO.getUser(userId);
        String refeere = user.getUserReferee();
        Integer refeereLevel = 0;
        while (true) {
            User userRefeere = userBO.getUser(refeere);
            if (userRefeere == null) {
                break;
            }
            userRefeere.setRefeereLevel(refeereLevel + 1);
            list.add(userRefeere);
            refeereLevel++;
            refeere = userRefeere.getUserReferee();
            if (StringUtils.isBlank(refeere)) {
                break;
            }
        }
        return list;
    }

    @Override
    public User doGetUser(String userId) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        } else {
            User userReferee = userBO.getUser(user.getUserReferee());
            if (userReferee != null) {
                user.setUserRefereeName(userReferee.getLoginName());
            }
            // 获取用户扩展信息
            UserExt userExt = userExtBO.getUserExt(userId);
            user.setUserExt(userExt);

            user.setTotalFansNum(0L);
            user.setTotalFollowNum(0L);
            // 获取我关注的人
            UserRelation toCondition = new UserRelation();
            toCondition.setUserId(userId);
            toCondition.setStatus(EBoolean.YES.getCode());
            user.setTotalFollowNum(userRelationBO.getTotalCount(toCondition));
            // 获取我粉丝的人
            UserRelation condition = new UserRelation();
            condition.setToUser(userId);
            condition.setStatus(EBoolean.YES.getCode());
            user.setTotalFansNum(userRelationBO.getTotalCount(condition));
        }
        return user;
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        userBO.checkTradePwd(userId, tradePwd);
    }

    @Override
    public void sendAppSms(String userId, String content) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        smsOutBO.sendSmsOut(user.getMobile(), content, "805903");
    }

    /** 
     * @see com.std.user.ao.IUserAO#editLoginName(java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public void editLoginName(String userId, String loginName) {
        fieldTimesBO.isFieldTimesExist(EFieldType.LOGINNAME, userId);
        // 判断原登录名和现登录是否一致
        User user = userBO.getUser(userId);
        if (user.getLoginName().equalsIgnoreCase(loginName)) {
            throw new BizException("xn000000", "现登录名和原来一致，无需修改");
        }
        // 判断登录名是否已存在,全系统唯一
        userBO.isLoginNameExist(loginName, null, user.getSystemCode());
        if (StringUtils.isNotBlank(userId)) {
            userBO.refreshLoginName(userId, loginName);
            fieldTimesBO.saveFieldTimes(EFieldType.LOGINNAME, userId);
        } else {
            throw new BizException("xn000000", "用户ID不存在");
        }
    }

    @Override
    @Transactional
    public void editNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            userBO.refreshNickname(userId, nickname);
            fieldTimesBO.saveFieldTimes(EFieldType.NICKNAME, userId);
        } else {
            throw new BizException("xn000000", "用户ID不存在");
        }
    }

    /** 
     * @see com.std.user.ao.IUserAO#doTransfer(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public void doTransfer(String userId, String direction, Long amount,
            String remark, String refNo) {
        EBizType bizType = null;
        Long transAmount = null;
        if (EDirection.PLUS.getCode().equalsIgnoreCase(direction)) {
            bizType = EBizType.AJ_SR;
            transAmount = amount;
        }
        if (EDirection.MINUS.getCode().equalsIgnoreCase(direction)) {
            bizType = EBizType.AJ_ZC;
            transAmount = -amount;
        }
        // 资金变动
        userBO.refreshAmount(userId, transAmount, refNo, bizType, remark);
    }

    /** 
     * @see com.std.user.ao.IUserAO#doTransfer(java.lang.String, java.lang.String, com.std.user.enums.ERuleKind, com.std.user.enums.ERuleType, java.lang.String)
     */
    @Override
    public void doTransfer(String userId, String direction, String ruleType,
            String refNo) {
        User user = userBO.getUser(userId);
        ERuleType eRuleType = ERuleType.getRuleTypeMap().get(ruleType);
        if (null == eRuleType) {
            throw new BizException("xn000000", "产生规则项不存在");
        }
        Long amount = ruleBO.getRuleByCondition(ERuleKind.JF, eRuleType,
            user.getLevel());
        if (amount != 0) {
            doTransfer(userId, direction, amount, eRuleType.getValue(), refNo);
        }
    }

    @Override
    @Transactional
    public void doTransferAdd(String fromUser, String toUser, Long amount,
            String remark, String refNo) {
        Long transAmount = null;
        if (amount != null && amount != 0L) {
            EBizType fromBizType = null;
            EBizType toBizType = null;
            transAmount = amount;
            fromBizType = EBizType.AJ_ZC;
            toBizType = EBizType.AJ_SR;
            // 来方资金变动
            userBO.refreshAmount(fromUser, -transAmount, refNo, fromBizType,
                remark);
            // 来方资金变动
            userBO.refreshAmount(toUser, transAmount, refNo, toBizType, remark);
        }
    }

    private String getAccountType(String kind) {
        String accountType = null;
        if (EUserKind.F1.getCode().equals(kind)) {
            accountType = EAccountType.Customer.getCode();
        } else if (EUserKind.F2.getCode().equals(kind)) {
            accountType = EAccountType.Business.getCode();
        } else if (EUserKind.Operator.getCode().equals(kind)) {
            accountType = EAccountType.Plat.getCode();
        }
        return accountType;
    }
}
