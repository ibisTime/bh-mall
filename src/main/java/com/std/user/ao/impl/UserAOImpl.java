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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.user.ao.IUserAO;
import com.std.user.bo.IAJourBO;
import com.std.user.bo.IAccountBO;
import com.std.user.bo.ICPasswordBO;
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
import com.std.user.common.PwdUtil;
import com.std.user.common.SysConstant;
import com.std.user.common.WechatConstant;
import com.std.user.core.OrderNoGenerater;
import com.std.user.domain.CPassword;
import com.std.user.domain.Company;
import com.std.user.domain.SYSRole;
import com.std.user.domain.User;
import com.std.user.domain.UserExt;
import com.std.user.domain.UserRelation;
import com.std.user.dto.res.XN001400Res;
import com.std.user.dto.res.XN798011Res;
import com.std.user.dto.res.XN798012Res;
import com.std.user.dto.res.XN805151Res;
import com.std.user.dto.res.XN805154Res;
import com.std.user.dto.res.XN805155Res;
import com.std.user.enums.EAccountType;
import com.std.user.enums.EBizType;
import com.std.user.enums.EBoolean;
import com.std.user.enums.ECPwdType;
import com.std.user.enums.ECurrency;
import com.std.user.enums.EFieldType;
import com.std.user.enums.EIDKind;
import com.std.user.enums.ELoginType;
import com.std.user.enums.EPrefixCode;
import com.std.user.enums.ERuleKind;
import com.std.user.enums.ERuleType;
import com.std.user.enums.ESex;
import com.std.user.enums.ESysUser;
import com.std.user.enums.ESystemCode;
import com.std.user.enums.EUser;
import com.std.user.enums.EUserKind;
import com.std.user.enums.EUserLevel;
import com.std.user.enums.EUserPwd;
import com.std.user.enums.EUserStatus;
import com.std.user.exception.BizException;
import com.std.user.http.PostSimulater;
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

    @Autowired
    protected ICPasswordBO cPasswordBO;

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

    /** 
     * @see com.std.user.ao.IUserAO#doCheckLoginPwd(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        User condition = new User();
        condition.setUserId(userId);
        List<User> userList1 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    @Override
    @Transactional
    public String doRegister(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String kind, String isRegHx, String province, String city,
            String area, String systemCode) {
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805041", systemCode);
        // 插入用户信息
        String userId = userBO.doRegister(mobile, null, mobile, loginPwd,
            loginPwdStrength, userReferee, kind, EUserLevel.ZERO.getCode(), 0L,
            null, null, null, systemCode);
        // 新增扩展信息
        userExtBO.saveUserExt(userId, province, city, area, systemCode);
        if (EBoolean.YES.getCode().equals(isRegHx)) {
            // 注册环信
            instantMsgImpl.doRegisterUser(userId, systemCode);
        }
        // 分配账号
        if (ESystemCode.ZHPAY.getCode().equals(systemCode)) {
            List<String> currencyList = new ArrayList<String>();
            if (EUserKind.F2.getCode().equals(kind)) {
                currencyList.add(ECurrency.ZH_FRB.getCode());
            } else {
                currencyList.add(ECurrency.ZH_FRB.getCode());
                currencyList.add(ECurrency.CNY.getCode());
                currencyList.add(ECurrency.ZH_GXZ.getCode());
                currencyList.add(ECurrency.ZH_QBB.getCode());
                currencyList.add(ECurrency.ZH_GWB.getCode());
                currencyList.add(ECurrency.ZH_HBB.getCode());
                currencyList.add(ECurrency.ZH_HBYJ.getCode());
            }
            accountBO.distributeAccountList(userId, mobile,
                getAccountType(kind), currencyList, systemCode);
        } else if (ESystemCode.CAIGO.getCode().equals(systemCode)) {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.CG_JF.getCode());
            currencyList.add(ECurrency.CG_CGB.getCode());
            accountBO.distributeAccountList(userId, mobile,
                getAccountType(kind), currencyList, systemCode);
        } else {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.JF.getCode());
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805154", systemCode);
        // 插入用户信息
        String kind = EUserKind.F1.getCode();
        String userId = userBO.doRegister(mobile, null, mobile, loginPwd,
            loginPwdStrength, userReferee, kind, "0", 0L, null, null, null,
            systemCode);
        List<String> currencyList = new ArrayList<String>();
        if (ESystemCode.CAIGO.getCode().equals(systemCode)) {
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.CG_JF.getCode());
            currencyList.add(ECurrency.CG_CGB.getCode());
            accountBO.distributeAccountList(userId, mobile,
                getAccountType(kind), currencyList, systemCode);
        } else {
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.JF.getCode());
            accountBO.distributeAccountList(userId, mobile,
                getAccountType(kind), currencyList, systemCode);
        }
        accountBO.distributeAccountList(userId, mobile, getAccountType(kind),
            currencyList, systemCode);
        // 设置用户关系
        if (StringUtils.isNotBlank(userReferee)) {
            userRelationBO.saveUserRelation(userReferee, userId, systemCode);
        }
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        return new XN805154Res(userId, "0");
    }

    @Override
    @Transactional
    public String doCSWRegister(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String isRegHx, String companyCode, String systemCode) {
        userBO.isMobileExist(mobile, EUserKind.F1.getCode(), companyCode,
            systemCode);
        // 验证推荐人是否是平台的已注册用户
        userBO.checkUserReferee(userReferee, systemCode);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805076", systemCode);
        // 设置公司
        Company company = companyBO.getCompany(companyCode);
        if (company == null) {
            Company result = companyBO.getDefaultCompany(systemCode);
            if (result != null) {
                companyCode = result.getCode();
            }
        }
        // 插入用户信息
        String userId = userBO.doRegister(EPrefixCode.CSW.getCode() + mobile,
            null, mobile, loginPwd, loginPwdStrength, userReferee,
            EUserKind.F1.getCode(), EUserLevel.ONE.getCode(), 0L, companyCode,
            null, null, systemCode);
        // 注册
        Long amount = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.ZC,
            EBoolean.NO.getCode());
        List<String> currencyList = new ArrayList<String>();
        currencyList.add(ECurrency.CNY.getCode());
        currencyList.add(ECurrency.JF.getCode());
        accountBO.distributeAccountList(userId, mobile,
            EAccountType.Customer.getCode(), currencyList, systemCode);
        // 注册送积分
        accountBO.doTransferAmountRemote(ESysUser.SYS_USER_CSW.getCode(),
            userId, ECurrency.JF, amount, EBizType.AJ_REG, "注册送积分", "注册送积分");
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        if (EBoolean.YES.getCode().equals(isRegHx)) {
            instantMsgImpl.doRegisterUser(userId, systemCode);
        }
        return userId;
    }

    private String doThirdRegisterWechat(String openId, String nickname,
            String photo, String gender, String companyCode, String systemCode) {
        User condition = new User();
        condition.setOpenId(openId);
        condition.setSystemCode(systemCode);
        long count = userBO.getTotalCount(condition);
        if (count > 0) {
            throw new BizException("xn702002", openId + "第三方开放编号已存在");
        }
        // 插入用户信息
        String loginPwd = EUserPwd.InitPwd.getCode();
        String userId = userBO.doRegister(openId, nickname, null, loginPwd,
            "1", null, EUserKind.F1.getCode(), "0", 0L, companyCode, openId,
            null, systemCode);
        // 新增扩展信息
        userExtBO.saveUserExt(userId, photo, gender, systemCode);

        // 分配账号(人民币和虚拟币)
        if (ESystemCode.ZHPAY.getCode().equals(systemCode)) {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.ZH_FRB.getCode());
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.ZH_GXZ.getCode());
            currencyList.add(ECurrency.ZH_QBB.getCode());
            currencyList.add(ECurrency.ZH_GWB.getCode());
            currencyList.add(ECurrency.ZH_HBB.getCode());
            currencyList.add(ECurrency.ZH_HBYJ.getCode());
            accountBO.distributeAccountList(userId, nickname,
                getAccountType(EUserKind.F1.getCode()), currencyList,
                systemCode);
        } else {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.JF.getCode());
            accountBO.distributeAccountList(userId, nickname,
                getAccountType(EUserKind.F1.getCode()), currencyList,
                systemCode);
        }
        return userId;
    }

    private String doThirdRegisterWechat(String openId, String mobile,
            String nickname, String isRegHx, String photo, String gender,
            String companyCode, String systemCode) {
        User condition = new User();
        condition.setOpenId(openId);
        condition.setSystemCode(systemCode);
        long count = userBO.getTotalCount(condition);
        if (count > 0) {
            throw new BizException("xn702002", openId + "第三方开放编号已存在");
        }
        // 插入用户信息
        String loginPwd = EUserPwd.InitPwd.getCode();
        userBO.isMobileExist(mobile, EUserKind.F1.getCode(), companyCode,
            systemCode);
        String loginName = mobile;
        if (ESystemCode.CSW.getCode().equals(systemCode)) {
            loginName += EPrefixCode.CSW + loginName;
        } else {
            loginName += EPrefixCode.CD + loginName;
        }
        String userId = userBO.doRegister(loginName, nickname, mobile,
            loginPwd, "1", null, EUserKind.F1.getCode(), "0", 0L, companyCode,
            openId, null, systemCode);
        // 新增扩展信息
        userExtBO.saveUserExt(userId, photo, gender, systemCode);

        // 分配账号(人民币和虚拟币)
        if (ESystemCode.ZHPAY.getCode().equals(systemCode)) {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.ZH_FRB.getCode());
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.ZH_GXZ.getCode());
            currencyList.add(ECurrency.ZH_QBB.getCode());
            currencyList.add(ECurrency.ZH_GWB.getCode());
            currencyList.add(ECurrency.ZH_HBB.getCode());
            currencyList.add(ECurrency.ZH_HBYJ.getCode());
            accountBO.distributeAccountList(userId, nickname,
                getAccountType(EUserKind.F1.getCode()), currencyList,
                systemCode);
        } else {
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.JF.getCode());
            accountBO.distributeAccountList(userId, nickname,
                getAccountType(EUserKind.F1.getCode()), currencyList,
                systemCode);
        }
        if (systemCode.equals(ESystemCode.CSW.getCode())) {
            // 注册
            Long amount = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.ZC,
                EBoolean.NO.getCode());
            // 注册送积分
            accountBO
                .doTransferAmountRemote(ESysUser.SYS_USER_CSW.getCode(),
                    userId, ECurrency.JF, amount, EBizType.AJ_REG, "注册送积分",
                    "注册送积分");
        }
        // 发送初始化密码
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您已成功注册。初始化登录密码为" + loginPwd + "，请及时登录网站更改密码。", "805042",
            systemCode);
        if (EBoolean.YES.getCode().equals(isRegHx)) {
            // 注册环信
            instantMsgImpl.doRegisterUser(userId, systemCode);
        }
        return userId;
    }

    @Override
    @Transactional
    public String doAddUser(String loginName, String mobile, String idKind,
            String idNo, String realName, String userReferee, String updater,
            String remark, String kind, String pdf, String roleCode,
            String isRegHx, String province, String city, String area,
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
            userId = userBO.doAddUser(mobile, mobile, loginPsd, userReferee,
                realName, idKind, idNo, loginPsd, kind, "0", remark, updater,
                pdf, null, systemCode);
            // 三方认证
            // dentifyBO.doIdentify(userId, realName, idKind, idNo);
            if (StringUtils.isBlank(realName)) {
                realName = mobile;
            }
            // 分配账号(人民币和虚拟币)
            if (ESystemCode.ZHPAY.getCode().equals(systemCode)) {
                List<String> currencyList = new ArrayList<String>();
                if (EUserKind.F2.getCode().equals(kind)) {
                    currencyList.add(ECurrency.ZH_FRB.getCode());
                } else {
                    currencyList.add(ECurrency.ZH_FRB.getCode());
                    currencyList.add(ECurrency.CNY.getCode());
                    currencyList.add(ECurrency.ZH_GXZ.getCode());
                    currencyList.add(ECurrency.ZH_QBB.getCode());
                    currencyList.add(ECurrency.ZH_GWB.getCode());
                    currencyList.add(ECurrency.ZH_HBB.getCode());
                    currencyList.add(ECurrency.ZH_HBYJ.getCode());
                }
                accountBO.distributeAccountList(userId, mobile,
                    getAccountType(kind), currencyList, systemCode);
            } else if (ESystemCode.CAIGO.getCode().equals(systemCode)) {
                List<String> currencyList = new ArrayList<String>();
                currencyList.add(ECurrency.CNY.getCode());
                currencyList.add(ECurrency.CG_JF.getCode());
                currencyList.add(ECurrency.CG_CGB.getCode());
                accountBO.distributeAccountList(userId, mobile,
                    getAccountType(kind), currencyList, systemCode);
                if (EUserKind.F1.getCode().equals(kind)) {
                    userRelationBO.saveUserRelation(userReferee, userId,
                        systemCode);
                }
            } else {
                List<String> currencyList = new ArrayList<String>();
                currencyList.add(ECurrency.CNY.getCode());
                currencyList.add(ECurrency.JF.getCode());
                accountBO.distributeAccountList(userId, mobile,
                    getAccountType(kind), currencyList, systemCode);
            }
            // 新增扩展信息
            userExtBO.saveUserExt(userId, province, city, area, systemCode);
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您已成功注册。初始化登录密码为" + loginPsd + "，请及时登录网站更改密码。",
                "805042", systemCode);
        } else if (EUserKind.Operator.getCode().equals(kind)) {
            // 验证登录名
            userBO.isLoginNameExist(loginName, kind, systemCode);
            loginPsd = EUserPwd.InitPwd.getCode();
            // 插入用户信息
            userId = userBO.doAddUser(loginName, mobile, loginPsd, userReferee,
                realName, idKind, idNo, loginPsd, kind,
                EUserLevel.ZERO.getCode(), remark, updater, pdf, roleCode,
                systemCode);
            // 新增扩展信息
            userExtBO.saveUserExt(userId, province, city, area, systemCode);
            // 民宿主
        } else if (EUserKind.Partner.getCode().equals(kind)) {
            // 验证登录名
            userBO.isLoginNameExist(loginName, kind, systemCode);
            loginPsd = RandomUtil.generate6();
            // 插入用户信息
            userId = userBO.doAddUser(loginName, mobile, loginPsd, userReferee,
                realName, idKind, idNo, loginPsd, kind,
                EUserLevel.ZERO.getCode(), remark, updater, pdf, roleCode,
                systemCode);
            // 新增扩展信息
            userExtBO.saveUserExt(userId, province, city, area, systemCode);
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECurrency.CNY.getCode());
            accountBO.distributeAccountList(userId, mobile,
                getAccountType(kind), currencyList, systemCode);
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您已成功注册。您的登录名[" + loginName + "],登录密码[" + loginPsd
                    + "]。请及时登录修改密码", "805042", systemCode);
        } else if (EUserKind.JMS.getCode().equals(kind)) {
            userId = doAddCaigoOss(loginName, mobile, idKind, idNo, realName,
                userReferee, updater, remark, kind, pdf, systemCode, loginPsd);
        }
        // 是则注册环信用户
        if (EBoolean.YES.getCode().equals(isRegHx)) {
            instantMsgImpl.doRegisterUser(userId, systemCode);
        }
        return userId;
    }

    private String doAddCaigoOss(String loginName, String mobile,
            String idKind, String idNo, String realName, String userReferee,
            String updater, String remark, String kind, String pdf,
            String systemCode, String loginPsd) {
        String userId;
        // 验证登录名
        userBO.isLoginNameExist(loginName, kind, systemCode);
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
        if (EUserKind.JMS.getCode().equals(kind)) {
            cxRoleCode = PropertiesUtil.Config.JMS_ROLECODE;
        }
        // 插入用户信息
        userId = userBO.doAddUser(loginName, mobile, loginPsd, userReferee,
            realName, idKind, idNo, loginPsd, kind, level + "", remark,
            updater, pdf, cxRoleCode, systemCode);
        // 分配账号(人民币,积分币和菜狗币)
        List<String> currencyList = new ArrayList<String>();
        currencyList.add(ECurrency.CNY.getCode());
        currencyList.add(ECurrency.CG_JF.getCode());
        currencyList.add(ECurrency.CG_CGB.getCode());
        accountBO.distributeAccountList(userId, mobile, getAccountType(kind),
            currencyList, systemCode);
        return userId;
    }

    @Override
    public String doAddUser(String loginName, String kind, String roleCode,
            String updater, String systemCode) {
        // 验证登录名
        userBO.isLoginNameExist(loginName, kind, systemCode);
        // 插入用户信息
        String loginPsd = EUserPwd.InitPwd.getCode();
        String userId = userBO.doAddUser(loginName, null, loginPsd, null, null,
            null, null, loginPsd, kind, null, null, updater, null, roleCode,
            systemCode);
        List<String> currencyList = new ArrayList<String>();
        currencyList.add(ECurrency.CNY.getCode());
        currencyList.add(ECurrency.JF.getCode());
        accountBO.distributeAccountList(userId, loginName,
            getAccountType(kind), currencyList, systemCode);
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
        List<String> currencyList = new ArrayList<String>();
        currencyList.add(ECurrency.CNY.getCode());
        accountBO.distributeAccountList(userId, realName, getAccountType(kind),
            currencyList, systemCode);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您已成功注册。您的登录密码为" + loginPsd + ";支付密码为" + tradePsd
                + "，请及时登录个金所网站修改密码。如有疑问，请联系客服：400-0008-139。", "805042",
            systemCode);
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
        // 插入用户信息
        String loginPwd = RandomUtil.generate6();
        String userId = userBO.doRegister(EPrefixCode.CSW.getCode() + mobile,
            null, mobile, loginPwd, "1", userReferee, EUserKind.F1.getCode(),
            EUserLevel.ZERO.getCode(), 0L, companyCode, null, null, systemCode);
        // 环信代注册
        instantMsgImpl.doRegisterUser(userId, systemCode);
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您已成功注册。初始化登录密码为" + loginPwd + "，请及时登录网站更改密码。", "805079",
            systemCode);
        return userId;
    }

    @Override
    public String doAddUserWithPwd(String mobile, String loginPwd,
            String userReferee, String updater, String remark, String isRegHx,
            String companyCode, String systemCode) {
        String kind = EUserKind.F1.getCode();
        // 验证手机号
        userBO.isMobileExist(mobile, kind, systemCode, systemCode);
        // 插入用户信息
        String isSendSms = EBoolean.NO.getCode();
        String loginPwdMw = RandomUtil.generate6();
        if (StringUtils.isBlank(loginPwd)) {
            loginPwd = MD5Util.md5(loginPwdMw);
            isSendSms = EBoolean.YES.getCode();
        }
        String userId = userBO.doAddUser(mobile, loginPwd, userReferee, kind,
            remark, updater, companyCode, systemCode);
        // 设置用户关系
        if (StringUtils.isNotBlank(userReferee)) {
            userRelationBO.saveUserRelation(userReferee, userId, systemCode);
        }
        // 新增扩展信息
        userExtBO.saveUserExt(userId, systemCode);
        List<String> currencyList = new ArrayList<String>();
        if (ESystemCode.CAIGO.getCode().equals(systemCode)) {
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.CG_JF.getCode());
            currencyList.add(ECurrency.CG_CGB.getCode());
        } else {
            currencyList.add(ECurrency.CNY.getCode());
            currencyList.add(ECurrency.JF.getCode());
        }
        accountBO.distributeAccountList(userId, mobile, getAccountType(kind),
            currencyList, systemCode);
        if (EBoolean.YES.getCode().equals(isRegHx)) {
            // 注册环信
            instantMsgImpl.doRegisterUser(userId, systemCode);
        }
        // 发送短信
        if (EBoolean.YES.getCode().equals(isSendSms)) {
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您已成功注册。您的登录密码为" + loginPwdMw + "，请及时登录网站修改!",
                "001301", systemCode);
        }
        return userId;
    }

    @Override
    @Transactional
    public String doAddPartner(User user, String province, String city,
            String area) {
        String kind = EUserKind.Partner.getCode();
        // 合伙人新增业务逻辑：
        // 1、校验登录是否全系统唯一，校验辖区是否已经存在合伙人
        userBO.isLoginNameExist(user.getLoginName(),
            EUserKind.Partner.getCode(), user.getSystemCode());
        UserExt condition = new UserExt();
        condition.setKind(kind);
        condition.setProvince(province);
        condition.setCity(city);
        condition.setArea(area);
        List<UserExt> list = userExtBO.queryUserExtList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn000000", "该辖区已存在合伙人！");
        }
        // 2、新增用户，分配角色，增加账户
        String loginPwd = EUserPwd.InitPwd.getCode();
        user.setLoginPwd(loginPwd);
        user.setKind(kind);
        user.setRoleCode(PropertiesUtil.Config.PARTNER_ROLECODE);

        String userId = OrderNoGenerater.generate("U");
        user.setUserId(userId);
        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        user.setLevel(EUserLevel.ZERO.getCode());
        user.setStatus(EUserStatus.NORMAL.getCode());
        if (StringUtils.isBlank(user.getCompanyCode())) {
            user.setCompanyCode(user.getSystemCode());
        }
        userBO.doAddUser(user);
        userExtBO.saveUserExt(userId, province, city, area,
            user.getSystemCode());
        List<String> currencyList = new ArrayList<String>();
        currencyList.add(ECurrency.ZH_FRB.getCode());
        currencyList.add(ECurrency.CNY.getCode());
        currencyList.add(ECurrency.ZH_GXZ.getCode());
        currencyList.add(ECurrency.ZH_QBB.getCode());
        currencyList.add(ECurrency.ZH_GWB.getCode());
        currencyList.add(ECurrency.ZH_HBB.getCode());
        currencyList.add(ECurrency.ZH_HBYJ.getCode());
        accountBO.distributeAccountList(userId, user.getLoginName(),
            EAccountType.Partner.getCode(), currencyList, user.getSystemCode());
        return userId;
    }

    /** 
     * @see com.std.user.ao.IUserAO#doEditPartner(com.std.user.domain.User, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public void doEditPartner(User user, String province, String city,
            String area) {
        String kind = EUserKind.Partner.getCode();
        // 合伙人修改业务逻辑：
        // 1、校验登录是否全系统唯一，校验辖区是否已存在合伙人
        User dbUser = userBO.getUser(user.getUserId(), user.getSystemCode());
        if (dbUser == null) {
            throw new BizException("xn000000", "该用户编号不存在！");
        }
        if (!dbUser.getLoginName().equals(user.getLoginName())) {
            userBO.isLoginNameExist(user.getLoginName(),
                EUserKind.Partner.getCode(), user.getSystemCode());
        }
        UserExt condition = new UserExt();
        condition.setKind(kind);
        condition.setProvince(province);
        condition.setCity(city);
        condition.setArea(area);
        List<UserExt> list = userExtBO.queryUserExtList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            UserExt userExt = list.get(0);
            if (!userExt.getUserId().equals(user.getUserId())) {
                throw new BizException("xn000000", "该辖区已存在合伙人！");
            }
        }
        // 2、修改用户扩展信息
        userBO.refreshUser(user);
        UserExt userExt = userExtBO.getUserExt(user.getUserId());
        userExt.setProvince(province);
        userExt.setCity(city);
        userExt.setArea(area);
        userExtBO.refreshUserExt(userExt);
    }

    @Override
    public String doLogin(String loginName, String loginPwd, String kind,
            String companyCode, String systemCode) {
        User condition = new User();
        if (EUserKind.F1.getCode().equals(kind)
                || EUserKind.F2.getCode().equals(kind)) {
            condition.setLoginName(loginName);
            condition.setLoginType(ELoginType.ALL.getCode());
            condition.setCompanyCode(companyCode);
        } else {
            condition.setLoginName(loginName);
        }
        condition.setKind(kind);
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
            throw new BizException("xn702002", "账户已被锁定，请联系工作人员");
        }
        return user.getUserId();
    }

    @Override
    public String doCaptchaLoginReg(String mobile, String kind,
            String smsCaptcha, String companyCode, String systemCode) {
        String userId = null;
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805183", companyCode,
            systemCode);
        User user = userBO.getUserByMobileAndKind(mobile, kind, companyCode,
            systemCode);
        if (user == null) {
            User data = new User();
            data.setMobile(mobile);
            data.setKind(kind);
            data.setCompanyCode(companyCode);
            data.setSystemCode(systemCode);
            userId = userBO.saveUser(data);
        } else {
            userId = user.getUserId();
        }
        return userId;
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
            String realName, String isReal) {
        User user = userBO.getUser(userId, null);
        if (EBoolean.YES.getCode().equals(isReal)) {
            // 三方认证
            dentifyBO.doIdentify(user.getSystemCode(), user.getCompanyCode(),
                userId, realName, idKind, idNo);
        }
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // 回写Account表realName;
        accountBO.refreshRealName(user.getUserId(), realName,
            user.getSystemCode());
    }

    @Override
    public void doFourIdentify(String userId, String idKind, String idNo,
            String realName, String cardNo, String bindMobile) {
        User user = userBO.getUser(userId, null);
        // 三方认证
        dentifyBO.doFourIdentify(userId, realName, idKind, idNo, cardNo,
            bindMobile);
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // 回写Account表realName;
        accountBO.refreshRealName(user.getUserId(), realName,
            user.getSystemCode());
    }

    @Override
    public Object doZhimaIdentify(String userId, String idKind, String idNo,
            String realName) {
        User user = userBO.getUser(userId, null);
        // 判断库中是否有该记录
        userBO.checkIdentify(user.getKind(), idKind, idNo, realName);
        // 芝麻认证 有两种结果：如果本地有记录，返回成功；如果本地无记录，返货芝麻认证所需信息
        XN798011Res res = dentifyBO.doZhimaVerify(user.getSystemCode(),
            user.getSystemCode(), userId, idKind, idNo, realName);
        // 如果直接返回成功
        if (res.isSuccess()) {
            // 更新用户表
            userBO.refreshIdentity(userId, realName, EIDKind.IDCard.getCode(),
                idNo);
            // 回写Account表realName;
            accountBO.refreshRealName(user.getUserId(), realName,
                user.getSystemCode());
        }
        return res;
    }

    @Override
    public Object doZhimaQuery(String userId, String bizNo) {
        User user = userBO.getUser(userId, null);
        XN798012Res res = dentifyBO.doZhimaQuery(user.getSystemCode(),
            user.getSystemCode(), bizNo);
        if (res.isSuccess()) {
            // 更新用户表
            userBO.refreshIdentity(userId, res.getRealName(), res.getIdKind(),
                res.getIdNo());
            // 回写Account表realName;
            accountBO.refreshRealName(user.getUserId(), res.getRealName(),
                user.getSystemCode());
        }
        return res;
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
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805045",
            user.getSystemCode());
        userBO.refreshTradePwd(userId, tradePwd, tradePwdStrength);
        // 发送短信
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的支付密码设置成功。请妥善保管您的账户相关信息。", "805045",
            user.getSystemCode());
    }

    @Override
    @Transactional
    public void doIdentifySetTradePwd(String userId, String idKind,
            String idNo, String realName, String tradePwd,
            String tradePwdStrength, String smsCaptcha) {
        User user = userBO.getUser(userId, null);
        // 三方认证
        dentifyBO.doIdentify(user.getSystemCode(), user.getCompanyCode(),
            userId, realName, idKind, idNo);
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // 回写Account表realName;
        // accountBO.refreshRealName(userId, realName);

        // 开始支付密码设置
        // 判断是否和登录密码重复
        user = this.doGetUser(userId);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805046",
            user.getSystemCode());
        userBO.refreshTradePwd(userId, tradePwd, tradePwdStrength);
        // 发送短信
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您已通过实名认证，且支付密码设置成功。请妥善保管您的账户相关信息。", "805046",
            user.getSystemCode());
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
                user.getCompanyCode(), user.getSystemCode());
        } else {
            userBO.isMobileExist(newMobile, user.getKind(),
                user.getSystemCode());
        }
        // 验证支付密码
        if (StringUtils.isNotBlank(tradePwd)) {
            userBO.checkTradePwd(userId, tradePwd);
        }
        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805047",
            user.getSystemCode());
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
                    + "，请妥善保管您的账户相关信息。", "805047", user.getSystemCode());
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 验证手机号
        userBO.isMobileExist(newMobile, user.getKind(), user.getCompanyCode(),
            user.getSystemCode());
        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805047",
            user.getSystemCode());
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
                    + "，请妥善保管您的账户相关信息。", "805047", user.getSystemCode());
    }

    @Override
    public void doBindMoblie(String userId, String mobile, String smsCaptcha,
            String isSendSms) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            throw new BizException("li01004", "手机号已经绑定，无需再次操作");
        }
        // 验证手机号
        userBO.isMobileExist(mobile, EUserKind.F1.getCode(),
            user.getCompanyCode(), user.getSystemCode());
        // 短信验证码是否正确（往手机号发送）
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805153",
            user.getCompanyCode(), user.getSystemCode());
        // 插入用户信息
        String loginPwd = RandomUtil.generate6();
        userBO.refreshBindMobile(userId, mobile, mobile, loginPwd, "1");
        // 发送短信
        if (EBoolean.YES.getCode().equals(isSendSms)) {
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的登录密码为" + loginPwd + "，请及时登录网站更改密码。", "805153",
                user.getSystemCode());
        }
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805048", systemCode);
        userBO.refreshLoginPwd(user.getUserId(), MD5Util.md5(newLoginPwd),
            loginPwdStrength);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码找回成功。请妥善保管您的账户相关信息。", "805048", systemCode);
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805171", companyCode,
            systemCode);
        userBO.refreshLoginPwd(user.getUserId(), MD5Util.md5(newLoginPwd),
            loginPwdStrength);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码找回成功。请妥善保管您的账户相关信息。", "805171", systemCode);
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
        smsOutBO.sendCaptcha(data.getMobile(), "805059", systemCode);
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
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805059",
            systemCode);
        userBO.refreshLoginPwd(user.getUserId(), MD5Util.md5(newLoginPwd),
            loginPwdStrength);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码找回成功。请妥善保管您的账户相关信息。", "805059", systemCode);
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
                    + "用户，您的登录密码修改成功。请妥善保管您的账户相关信息。", "805049",
                user.getSystemCode());
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805050",
            user.getSystemCode());
        userBO.refreshTradePwd(userId, newTradePwd, tradePwdStrength);
        String userKind = user.getKind();
        if (EUserKind.F1.getCode().equals(userKind)
                || EUserKind.F2.getCode().equals(userKind)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的支付密码找回成功。请妥善保管您的账户相关信息。", "805050",
                user.getSystemCode());
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805057",
            user.getSystemCode());
        userBO.refreshTradePwd(userId, newTradePwd, tradePwdStrength);
        // 发送短信
        String userKind = user.getKind();
        if (EUserKind.F1.getCode().equals(userKind)
                || EUserKind.F2.getCode().equals(userKind)) {
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的支付密码找回成功。请妥善保管您的账户相关信息。", "805057",
                user.getSystemCode());
        }
    }

    @Override
    @Transactional
    public void doResetTradePwd(String userId, String oldTradePwd,
            String newTradePwd, String tradePwdStrength) {
        if (oldTradePwd.equals(newTradePwd)) {
            throw new BizException("li01008", "新支付密码与原有支付密码重复");
        }
        User conditon = new User();
        conditon.setUserId(userId);
        conditon.setTradePwd(MD5Util.md5(oldTradePwd));
        List<User> list = userBO.queryUserList(conditon);
        User user = null;
        if (CollectionUtils.isNotEmpty(list)) {
            user = list.get(0);
        } else {
            throw new BizException("li01008", "旧支付密码不正确");
        }
        userBO.refreshTradePwd(userId, newTradePwd, tradePwdStrength);
        // 发送短信
        String userKind = user.getKind();
        if (EUserKind.F1.getCode().equals(userKind)
                || EUserKind.F2.getCode().equals(userKind)) {
            String mobile = user.getMobile();
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的支付密码修改成功。请妥善保管您的账户相关信息。", "805051",
                user.getSystemCode());
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
                    + smsContent, "805052", user.getSystemCode());
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
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        }
        String refeere = user.getUserReferee();
        // 获取上级
        User userRefeereTop1 = getTopUserRefeere(refeere, -1);
        if (userRefeereTop1 != null) {
            list.add(userRefeereTop1);
            // User userRefeereTop2 = getTopUserRefeere(
            // userRefeereTop1.getUserReferee(), -2);
            // if (userRefeereTop2 != null) {
            // list.add(userRefeereTop2);
            // }
        }

        // 获取下级，下下级，下下下级
        List<User> refeeresNext1 = getNextUserRefeere(userId, 1);
        if (CollectionUtils.isNotEmpty(refeeresNext1)) {
            list.addAll(refeeresNext1);
            for (User userNext2 : refeeresNext1) {
                List<User> refeeresNext2 = getNextUserRefeere(
                    userNext2.getUserId(), 2);
                if (CollectionUtils.isNotEmpty(refeeresNext2)) {
                    list.addAll(refeeresNext2);
                }
                for (User userNext3 : refeeresNext2) {
                    List<User> refeeresNext3 = getNextUserRefeere(
                        userNext3.getUserId(), 3);
                    if (CollectionUtils.isNotEmpty(refeeresNext3)) {
                        list.addAll(refeeresNext3);
                    }
                }
            }
        }
        return list;
    }

    private User getTopUserRefeere(String userId, int refeereLevel) {
        User userRefeere = userBO.getUser(userId);
        if (userRefeere != null) {
            userRefeere.setRefeereLevel(refeereLevel);
            UserExt userExt = userExtBO.getUserExt(userId);
            userRefeere.setUserExt(userExt);
        }
        return userRefeere;
    }

    private List<User> getNextUserRefeere(String userId, int refeereLevel) {
        List<User> userList = userBO.getUsersByUserReferee(userId);
        if (CollectionUtils.isNotEmpty(userList)) {
            for (User user : userList) {
                user.setRefeereLevel(refeereLevel);
                UserExt userExt = userExtBO.getUserExt(user.getUserId());
                user.setUserExt(userExt);
            }
        }
        return userList;
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

    /** 
     * @see com.std.user.ao.IUserAO#doGetUserIdByCondition(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String doGetUserIdByCondition(String mobile, String kind,
            String companyCode, String systemCode) {
        String userId = null;
        User condition = new User();
        condition.setMobile(mobile);
        condition.setKind(kind);
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<User> userList = userBO.queryUserList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            User user = userList.get(0);
            if (EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
                userId = user.getUserId();
            }
        }
        return userId;
    }

    @Override
    public void doCheckTradePwd(String userId, String tradePwd) {
        userBO.checkTradePwd(userId, tradePwd);
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
     * @see com.std.user.ao.IUserAO#doChangeCompany(java.lang.String, java.lang.String)
     */
    public void doChangeCompany(String userId, String companyCode) {
        userBO.refreshCompany(userId, companyCode);
    }

    private String getAccountType(String kind) {
        String accountType = null;
        if (EUserKind.F1.getCode().equals(kind)) {
            accountType = EAccountType.Customer.getCode();
        } else if (EUserKind.F2.getCode().equals(kind)) {
            accountType = EAccountType.Business.getCode();
        } else if (EUserKind.Partner.getCode().equals(kind)) {
            accountType = EAccountType.Partner.getCode();
        } else if (EUserKind.Operator.getCode().equals(kind)) {
            accountType = EAccountType.Plat.getCode();
        } else if (EUserKind.JMS.getCode().equals(kind)) {
            accountType = EAccountType.Business.getCode();
        }
        return accountType;
    }

    @Override
    @Transactional
    public XN805151Res doLoginWeChat(String code, String mobile,
            String isRegHx, String companyCode, String systemCode) {
        String userId = null;
        String isNeedMobile = "";
        String appId = "";
        String appSecret = "";
        CPassword condition = new CPassword();
        condition.setType("3");
        condition.setAccount("ACCESS_KEY");
        // 橙商户系统的系统，配置多个系统微信
        if (ESystemCode.CSH.getCode().equals(companyCode)) {
            condition.setCompanyCode(companyCode);
        }
        condition.setSystemCode(systemCode);
        List<CPassword> result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appId配置获取失败，请检查配置");
        }
        appId = result.get(0).getPassword();
        condition.setAccount("SECRET_KEY");
        result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appSecret配置获取失败，请检查配置");
        }
        appSecret = result.get(0).getPassword();

        // Step2：通过Authorization Code获取Access Token
        String accessToken = "";
        Map<String, String> res = new HashMap<>();
        Properties formProperties = new Properties();
        formProperties.put("grant_type", "authorization_code");
        formProperties.put("appid", appId);
        formProperties.put("secret", appSecret);
        formProperties.put("code", code);
        System.out.println(appId + " " + appSecret + " " + code);
        String response;
        try {
            response = PostSimulater.requestPostForm(
                WechatConstant.WX_TOKEN_URL, formProperties);
            res = getMapFromResponse(response);
            System.out.println(res);
            accessToken = (String) res.get("access_token");
            if (res.get("error") != null) {
                throw new BizException("XN000000", "微信登录失败，失原因："
                        + res.get("error"));
            }
            if (StringUtils.isBlank(accessToken)) {
                throw new BizException("XN000000", "accessToken不能为空");
            }
            // Step3：使用Access Token来获取用户的OpenID
            String openId = (String) res.get("openid");
            // 获取unionid
            Map<String, String> wxRes = new HashMap<>();
            Properties queryParas = new Properties();
            queryParas.put("access_token", accessToken);
            queryParas.put("openid", openId);
            queryParas.put("lang", "zh_CN");
            wxRes = getMapFromResponse(PostSimulater.requestPostForm(
                WechatConstant.WX_USER_INFO_URL, queryParas));
            System.out.println(wxRes);
            String unionid = (String) wxRes.get("unionid");
            if (StringUtils.isEmpty(unionid)) {
                unionid = (String) wxRes.get("openid");
            }
            // Step4：根据openId从数据库中查询用户信息（user）
            User userCondition = new User();
            userCondition.setOpenId(unionid);
            userCondition.setSystemCode(systemCode);
            List<User> users = userBO.queryUserList(userCondition);

            if (!CollectionUtils.isEmpty(users)) {
                // Step4-1：如果user存在，说明用户授权登录过，直接登录
                User user = users.get(0);
                if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
                    throw new BizException("10002", "用户被锁定");
                }
                userId = user.getUserId();
            } else {
                String name = (String) wxRes.get("nickname");
                String headimgurl = (String) wxRes.get("headimgurl");
                String sex = ESex.UNKNOWN.getCode();
                System.out.println("***性别=" + String.valueOf(wxRes.get("sex")));
                if (String.valueOf(wxRes.get("sex")).equals("1.0")) {
                    sex = ESex.MEN.getCode();
                } else if (String.valueOf(wxRes.get("sex")).equals("2.0")) {
                    sex = ESex.WOMEN.getCode();
                }
                if (StringUtils.isBlank(mobile)) {
                    isNeedMobile = EBoolean.YES.getCode();
                } else {
                    // 查找该手机号是否有过注册
                    User user = new User();
                    user.setMobile(mobile);
                    user.setKind(EUserKind.F1.getCode());
                    user.setSystemCode(systemCode);
                    List<User> userList = userBO.queryUserList(user);
                    if (CollectionUtils.isEmpty(userList)) {
                        userId = doThirdRegisterWechat(openId, mobile, name,
                            isRegHx, headimgurl, sex, companyCode, systemCode);
                    } else {
                        // 根据该手机号更新用户信息
                        userId = userList.get(0).getUserId();
                        userBO.refreshWxInfor(userId, openId, name);
                        userExtBO.refreshUserExt(userId, headimgurl, sex);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("xn000000", e.getMessage());
        }
        return new XN805151Res(userId, isNeedMobile);
    }

    @Override
    @Transactional
    public String doLoginWeChat(String code, String companyCode,
            String systemCode) {
        String userId = null;
        String appId = "";
        String appSecret = "";
        CPassword condition = new CPassword();
        condition.setType("3");
        condition.setAccount("ACCESS_KEY");
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<CPassword> result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appId配置获取失败，请检查配置");
        }
        appId = result.get(0).getPassword();
        condition.setAccount("SECRET_KEY");
        result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appSecret配置获取失败，请检查配置");
        }
        appSecret = result.get(0).getPassword();

        // Step2：通过Authorization Code获取Access Token
        String accessToken = "";
        Map<String, String> res = new HashMap<>();
        Properties formProperties = new Properties();
        formProperties.put("grant_type", "authorization_code");
        formProperties.put("appid", appId);
        formProperties.put("secret", appSecret);
        formProperties.put("code", code);
        System.out.println(appId + " " + appSecret + " " + code);
        String response;
        try {
            response = PostSimulater.requestPostForm(
                WechatConstant.WX_TOKEN_URL, formProperties);
            res = getMapFromResponse(response);
            System.out.println(res);
            accessToken = (String) res.get("access_token");
            if (res.get("error") != null || StringUtils.isBlank(accessToken)) {
                throw new BizException("XN000000", "获取accessToken失败");
            }
            // Step3：使用Access Token来获取用户的OpenID
            String openId = (String) res.get("openid");
            // 获取unionid
            Map<String, String> wxRes = new HashMap<>();
            Properties queryParas = new Properties();
            queryParas.put("access_token", accessToken);
            queryParas.put("openid", openId);
            queryParas.put("lang", "zh_CN");
            wxRes = getMapFromResponse(PostSimulater.requestPostForm(
                WechatConstant.WX_USER_INFO_URL, queryParas));
            System.out.println(wxRes);
            String unionid = (String) wxRes.get("unionid");
            if (StringUtils.isEmpty(unionid)) {
                unionid = (String) wxRes.get("openid");
            }
            // Step4：根据openId从数据库中查询用户信息（user）
            User userCondition = new User();
            userCondition.setOpenId(unionid);
            List<User> users = userBO.queryUserList(userCondition);

            if (!CollectionUtils.isEmpty(users)) {
                // Step4-1：如果user存在，说明用户授权登录过，直接登录
                User user = users.get(0);
                if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
                    throw new BizException("10002", "用户被锁定");
                }
                userId = user.getUserId();
            } else {
                String name = (String) wxRes.get("nickname");
                String headimgurl = (String) wxRes.get("headimgurl");
                String sex = ESex.UNKNOWN.getCode();
                System.out.println("***性别=" + String.valueOf(wxRes.get("sex")));
                if (String.valueOf(wxRes.get("sex")).equals("1.0")) {
                    sex = ESex.MEN.getCode();
                } else if (String.valueOf(wxRes.get("sex")).equals("2.0")) {
                    sex = ESex.WOMEN.getCode();
                }
                userId = doThirdRegisterWechat(openId, name, headimgurl, sex,
                    companyCode, systemCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("xn000000", e.getMessage());
        }

        return userId;
    }

    @Override
    @Transactional
    public String doLoginWeChat(String code, Long amount, String companyCode,
            String systemCode) {
        String userId = "";
        String appId = "";
        String appSecret = "";
        CPassword condition = new CPassword();
        condition.setType("3");
        condition.setAccount("ACCESS_KEY");
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<CPassword> result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appId配置获取失败，请检查配置");
        }
        appId = result.get(0).getPassword();
        condition.setAccount("SECRET_KEY");
        result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appSecret配置获取失败，请检查配置");
        }
        appSecret = result.get(0).getPassword();

        // Step2：通过Authorization Code获取Access Token
        String accessToken = "";
        Map<String, String> res = new HashMap<>();
        Properties formProperties = new Properties();
        formProperties.put("grant_type", "authorization_code");
        formProperties.put("appid", appId);
        formProperties.put("secret", appSecret);
        formProperties.put("code", code);
        System.out.println(appId + " " + appSecret + " " + code);
        String response;
        try {
            response = PostSimulater.requestPostForm(
                WechatConstant.WX_TOKEN_URL, formProperties);
            res = getMapFromResponse(response);
            accessToken = (String) res.get("access_token");
            if (res.get("error") != null || StringUtils.isBlank(accessToken)) {
                throw new BizException("XN000000", "获取accessToken失败");
            }
            // Step3：使用Access Token来获取用户的OpenID
            String openId = (String) res.get("openid");
            // 获取unionid
            Map<String, String> wxRes = new HashMap<>();
            Properties queryParas = new Properties();
            queryParas.put("access_token", accessToken);
            queryParas.put("openid", openId);
            queryParas.put("lang", "zh_CN");
            wxRes = getMapFromResponse(PostSimulater.requestPostForm(
                WechatConstant.WX_USER_INFO_URL, queryParas));
            System.out.println(wxRes);
            String unionid = (String) wxRes.get("unionid");
            if (StringUtils.isEmpty(unionid)) {
                unionid = (String) wxRes.get("openid");
            }
            // Step4：根据openId从数据库中查询用户信息（user）
            User userCondition = new User();
            userCondition.setOpenId(unionid);
            List<User> users = userBO.queryUserList(userCondition);

            if (!CollectionUtils.isEmpty(users)) {
                // Step4-1：如果user存在，说明用户授权登录过，直接登录
                User user = users.get(0);
                if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
                    throw new BizException("10002", "用户被锁定");
                }
                userId = user.getUserId();
            } else {
                String name = (String) wxRes.get("nickname");
                String headimgurl = (String) wxRes.get("headimgurl");
                String sex = ESex.UNKNOWN.getCode();
                System.out.println("***性别=" + String.valueOf(wxRes.get("sex")));
                if (String.valueOf(wxRes.get("sex")).equals("1.0")) {
                    sex = ESex.MEN.getCode();
                } else if (String.valueOf(wxRes.get("sex")).equals("2.0")) {
                    sex = ESex.WOMEN.getCode();
                }
                userId = doThirdRegisterWechat(openId, name, headimgurl, sex,
                    companyCode, systemCode);
                // 账户资金划拨
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_LLWW.getCode(), userId, ECurrency.JF,
                    amount, EBizType.AJ_REG, "注册送积分", "注册送积分");
                // 注册对其编号进行标注
                userId = userId + "&reg";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("xn000000", e.getMessage());
        }

        return userId;
    }

    /** 
     * @see com.std.user.ao.IUserAO#doLoginSmallWeChat(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String doLoginSmallWeChat(String jsCode, String nickname,
            String sex, String headimgurl, String companyCode, String systemCode) {
        String userId = "";
        String appId = "";
        String appSecret = "";
        CPassword condition = new CPassword();
        condition.setType(ECPwdType.XIAOCX.getCode());
        condition.setAccount("ACCESS_KEY");
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<CPassword> result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信小程序appId配置获取失败，请检查配置");
        }
        appId = result.get(0).getPassword();
        condition.setAccount("SECRET_KEY");
        result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信小程序appSecret配置获取失败，请检查配置");
        }
        appSecret = result.get(0).getPassword();

        // Step2：通过Authorization Code获取Access Token
        String sessionKey = "";
        Map<String, String> res = new HashMap<>();
        Properties formProperties = new Properties();
        formProperties.put("appid", appId);
        formProperties.put("secret", appSecret);
        formProperties.put("js_code", jsCode);
        formProperties.put("grant_type", "authorization_code");
        String response;
        try {
            response = PostSimulater.requestPostForm(
                WechatConstant.WX_JSCODE_SESSION_URL, formProperties);
            res = getMapFromResponse(response);
            sessionKey = (String) res.get("session_key");
            if (res.get("error") != null || StringUtils.isBlank(sessionKey)) {
                throw new BizException("XN000000", "获取sessionKey失败");
            }
            String openId = (String) res.get("openid");
            if (StringUtils.isBlank(openId)) {
                throw new BizException("XN000000", "获取openId失败");
            }
            SysConstant.JS_SESSIONKEY = sessionKey;
            // Step4：根据openId从数据库中查询用户信息（user）
            User userCondition = new User();
            userCondition.setOpenId(openId);
            List<User> users = userBO.queryUserList(userCondition);
            if (!CollectionUtils.isEmpty(users)) {
                // Step4-1：如果user存在，说明用户授权登录过，直接登录
                User user = users.get(0);
                if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
                    throw new BizException("10002", "用户被锁定");
                }
                userId = user.getUserId();
            } else {
                System.out.println("***性别=" + String.valueOf(sex));
                if (String.valueOf(sex).equals("1.0")) {
                    sex = ESex.MEN.getCode();
                } else if (String.valueOf(sex).equals("2.0")) {
                    sex = ESex.WOMEN.getCode();
                } else {
                    sex = ESex.UNKNOWN.getCode();
                }
                userId = doThirdRegisterWechat(openId, nickname, headimgurl,
                    sex, companyCode, systemCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("xn000000", e.getMessage());
        }

        return userId;
    }

    /**
     * @param response  可能是Json & Jsonp字符串 & urlParas
     *          eg：urlParas：access_token=xxx&expires_in=7776000&refresh_token=xxx
     * @return
     */
    public Map<String, String> getMapFromResponse(String response) {
        if (StringUtils.isBlank(response)) {
            return new HashMap<>();
        }

        Map<String, String> result = new HashMap<>();
        int begin = response.indexOf("{");
        int end = response.lastIndexOf("}") + 1;

        if (begin >= 0 && end > 0) {
            result = new Gson().fromJson(response.substring(begin, end),
                new TypeToken<Map<String, Object>>() {
                }.getType());
        } else {
            String[] paras = response.split("&");
            for (String para : paras) {
                result.put(para.split("=")[0], para.split("=")[1]);
            }
        }

        return result;
    }

    /** 
     * @see com.std.user.ao.IUserAO#doSuppleUser(com.std.user.domain.User)
     */
    @Override
    public void doSuppleUser(User data) {
        User user = userBO.getUser(data.getUserId());
        if (user == null) {
            throw new BizException("xn0110", "用户不存在");
        }
        if (StringUtils.isBlank(user.getMobile())) {
            userBO.refreshUserSupple(data);
        }
    }

    @Override
    public XN001400Res doGetDetailUser(String userId) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        }
        XN001400Res res = new XN001400Res();

        res.setUserId(userId);
        res.setOpenId(user.getOpenId());
        res.setLoginName(user.getLoginName());
        res.setNickname(user.getNickname());
        res.setMobile(user.getMobile());

        res.setStatus(user.getStatus());
        res.setLevel(user.getLevel());
        res.setKind(user.getKind());
        res.setRealName(user.getRealName());

        res.setUserReferee(user.getUserReferee());
        res.setIdKind(user.getIdKind());
        res.setIdNo(user.getIdNo());

        if (StringUtils.isNotBlank(user.getIdNo())) {
            res.setIdentityFlag(EBoolean.YES.getCode());
        } else {
            res.setIdentityFlag(EBoolean.NO.getCode());
        }
        if (StringUtils.isNotBlank(user.getTradePwdStrength())) {
            res.setTradepwdFlag(EBoolean.YES.getCode());
        } else {
            res.setTradepwdFlag(EBoolean.NO.getCode());
        }
        res.setTotalFollowNum(String.valueOf(user.getTotalFollowNum()));
        res.setTotalFansNum(String.valueOf(user.getTotalFansNum()));
        res.setSystemCode(user.getSystemCode());
        res.setCompanyCode(user.getCompanyCode());
        // 获取用户扩展信息
        UserExt userExt = userExtBO.getUserExt(userId);
        if (userExt != null) {
            res.setProvince(userExt.getProvince());
            res.setCity(userExt.getCity());
            res.setArea(userExt.getArea());
            res.setPhoto(userExt.getPhoto());
            res.setGender(userExt.getGender());
            res.setBirthday(userExt.getBirthday());
            res.setEmail(userExt.getEmail());
            res.setDiploma(userExt.getDiploma());
            res.setOccupation(userExt.getOccupation());
            res.setWorkTime(userExt.getWorkTime());
            res.setIntroduce(userExt.getIntroduce());
            res.setLatitude(userExt.getLatitude());
            res.setLongitude(userExt.getLongitude());
        }

        return res;
    }

    /** 
     * @see com.std.user.ao.IUserAO#upgradeLevel(java.lang.String, java.lang.String)
     */
    @Override
    public void upgradeLevel(String userId, String level) {
        User data = new User();
        data.setUserId(userId);
        data.setLevel(level);
        userBO.refreshLevel(data);
    }
}
