/**
 * @Title UserBOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:21:25 
 * @version V1.0   
 */
package com.std.user.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IUserBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.common.DateUtil;
import com.std.user.common.MD5Util;
import com.std.user.common.PhoneUtil;
import com.std.user.common.PwdUtil;
import com.std.user.core.OrderNoGenerater;
import com.std.user.dao.IAJourDAO;
import com.std.user.dao.ILevelRuleDAO;
import com.std.user.dao.IUserDAO;
import com.std.user.domain.AccountJour;
import com.std.user.domain.LevelRule;
import com.std.user.domain.User;
import com.std.user.enums.EAccountJourStatus;
import com.std.user.enums.EBizType;
import com.std.user.enums.EUserKind;
import com.std.user.enums.EUserLevel;
import com.std.user.enums.EUserStatus;
import com.std.user.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:21:25 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {
    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IAJourDAO aJourDAO;

    @Autowired
    private ILevelRuleDAO levelRuleDAO;

    /**
     * @see com.ibis.pz.user.IUserBO#refreshIdentity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateIdentity(data);
        }
        return count;
    }

    @Override
    public int refreshRealName(String userId, String realName) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateRealName(data);
        }
        return count;
    }

    @Override
    public int refreshTradePwd(String userId, String tradePwd,
            String tradePwdStrength) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setTradePwd(MD5Util.md5(tradePwd));
            data.setTradePwdStrength(tradePwdStrength);
            count = userDAO.updateTradePwd(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUser(java.lang.String)
     */
    @Override
    public User getUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByMobile(String mobile) {
        User data = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            List<User> list = userDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "手机号重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByMobileAndKind(String mobile, String kind) {
        User data = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            List<User> list = userDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "手机号重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByLoginName(String loginName) {
        User data = null;
        if (StringUtils.isNotBlank(loginName)) {
            User condition = new User();
            condition.setLoginName(loginName);
            List<User> list = userDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "登录名重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#queryUserList(com.User.pz.domain.UserDO)
     */
    @Override
    public List<User> queryUserList(User data) {
        return userDAO.selectList(data);
    }

    /**
     * 
     * @see com.ibis.pz.user.IUserBO#refreshLoginPwd(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshLoginPwd(String userId, String loginPwd,
            String loginPwdStrength) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginPwd(loginPwd);
            data.setLoginPwdStrength(loginPwdStrength);
            count = userDAO.updateLoginPwd(data);
        }
        return count;
    }

    @Override
    public int refreshMobile(String userId, String mobile) {
        // 手机号校验
        isMobileExist(mobile);
        User data = new User();
        data.setUserId(userId);
        data.setMobile(mobile);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateMobile(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#isMobileExist(java.lang.String)
     */
    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind("ff3");
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#isMobileExist(java.lang.String)
     */
    @Override
    public void isMobileExist(String mobile, String kind) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#isLoginNameExist(java.lang.String)
     */
    @Override
    public void isLoginNameExist(String loginName, String kind) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            User condition = new User();
            condition.setLoginName(loginName);
            condition.setKind(kind);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#checkUserReferee(java.lang.String)
     */
    @Override
    public void checkUserReferee(String userReferee) {
        if (StringUtils.isNotBlank(userReferee)) {
            // 判断格式
            User condition = new User();
            condition.setUserId(userReferee);
            long count = getTotalCount(condition);
            if (count <= 0) {
                throw new BizException("li01003", "推荐人不存在");
            }
        }
    }

    @Override
    public String doRegister(String loginName, String nickname, String mobile,
            String loginPwd, String loginPwdStrength, String userReferee,
            Long amount, String companyCode, String openId) {
        // 交易密码888888
        String tradePsd = "888888";
        String userId = null;
        if (StringUtils.isNotBlank(loginPwdStrength)) {
            User user = new User();
            userId = OrderNoGenerater.generate("U");
            user.setUserId(userId);
            user.setLoginName(loginName);
            user.setLoginPwd(MD5Util.md5(loginPwd));

            user.setLoginPwdStrength(loginPwdStrength);
            if (StringUtils.isBlank(nickname)) {
                user.setNickname(userId.substring(userId.length() - 8,
                    userId.length()));
            } else {
                user.setNickname(nickname);
            }
            user.setKind(EUserKind.F1.getCode());
            user.setLevel(EUserLevel.ONE.getCode());
            user.setUserReferee(userReferee);

            user.setMobile(mobile);
            user.setTradePwd(MD5Util.md5(tradePsd));
            user.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePsd));
            user.setStatus(EUserStatus.NORMAL.getCode());// 0正常;1程序锁定;2人工锁定
            user.setUpdater(userId);
            user.setUpdateDatetime(new Date());
            user.setRemark(EUserKind.F1.getValue());
            if (amount == null) {
                amount = 0L;
            }
            user.setAmount(amount);
            user.setLjAmount(amount);
            user.setCompanyCode(companyCode);
            user.setOpenId(openId);
            userDAO.insert(user);
        }
        return userId;
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(tradePwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setTradePwd(MD5Util.md5(tradePwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "交易密码错误");
            }
        } else {
            throw new BizException("jd00001", "交易密码错误");
        }
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "原登录密码错误");
            }
        } else {
            throw new BizException("jd00001", "原登录密码错误");
        }

    }

    @Override
    public String doAddUser(String loginName, String mobile, String loginPsd,
            String userReferee, String realName, String idKind, String idNo,
            String tradePsd, String kind, String level, String remark,
            String updater, String pdf, String roleCode) {
        String userId = null;
        if (StringUtils.isNotBlank(loginName) || StringUtils.isNotBlank(mobile)) {
            User user = new User();
            userId = OrderNoGenerater.generate("U");

            user.setUserId(userId);
            user.setLoginName(loginName);
            user.setLoginPwd(MD5Util.md5(loginPsd));
            user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPsd));
            user.setKind(kind);

            user.setLevel(level);
            user.setUserReferee(userReferee);
            user.setMobile(mobile);
            user.setIdKind(idKind);
            user.setIdNo(idNo);

            user.setRealName(realName);
            user.setTradePwd(MD5Util.md5(tradePsd));
            user.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePsd));
            user.setStatus(EUserStatus.NORMAL.getCode());// 0正常;1程序锁定;2人工锁定
            user.setUpdater(updater);

            user.setUpdateDatetime(new Date());
            user.setRemark(remark);
            user.setPdf(pdf);
            user.setRoleCode(roleCode);
            userDAO.insertRen(user);
        }
        return userId;
    }

    @Override
    public void refreshStatus(String userId, EUserStatus status,
            String updater, String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setStatus(status.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateStatus(data);
        }
    }

    @Override
    public void refreshRole(String userId, String roleCode, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setRoleCode(roleCode);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateRole(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshStatus(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void refreshPdf(String userId, String pdf, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setPdf(pdf);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updatePdf(data);
        }
    }

    @Override
    public void refreshLoginName(String userId, String loginName) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginName(loginName);
            userDAO.updateLoginName(data);
        }
    }

    @Override
    public void refreshNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setNickname(nickname);
            userDAO.updateNickname(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshAmount(java.lang.String, java.lang.Long, java.lang.String, com.std.user.enums.EBizType, java.lang.String)
     */
    @Override
    public void refreshAmount(String userId, Long transAmount, String refNo,
            EBizType bizType, String remark) {
        User dbUser = this.getUser(userId);
        Long nowAmount = dbUser.getAmount() + transAmount;
        if (nowAmount < 0) {
            throw new BizException("li779001", "亲，余额不足了哦");
        }
        User user = new User();
        user.setUserId(userId);
        user.setAmount(nowAmount);
        // 设置原来的用户等级
        user.setLevel(dbUser.getLevel());
        Long ljAmount = dbUser.getLjAmount();
        if (transAmount > 0) {
            ljAmount = ljAmount + transAmount;
            user.setLjAmount(ljAmount);
            // 重新设置等级
            LevelRule lrCondition = new LevelRule();
            List<LevelRule> lrList = levelRuleDAO.selectList(lrCondition);
            for (LevelRule levelRule : lrList) {
                if (ljAmount >= levelRule.getAmountMin()
                        && ljAmount < levelRule.getAmountMax()) {
                    user.setLevel(levelRule.getCode());
                    break;
                }
            }
        } else {
            user.setLjAmount(ljAmount);
        }
        userDAO.updateAmount(user);
        // 记录流水
        AccountJour accountJour = new AccountJour();
        accountJour.setBizType(bizType.getCode());
        accountJour.setRefNo(refNo);
        accountJour.setTransAmount(transAmount);
        accountJour.setPreAmount(dbUser.getAmount());
        accountJour.setPostAmount(nowAmount);
        accountJour.setRemark(remark);
        accountJour.setCreateDatetime(new Date());
        accountJour.setAccountNumber(userId);
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));
        aJourDAO.insert(accountJour);
    }
}
