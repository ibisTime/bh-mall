/**
 * @Package com.ibis.pz.user 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:48:42 
 * @version V1.0   
 */
package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.User;
import com.std.user.dto.res.XN805154Res;
import com.std.user.dto.res.XN805155Res;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:48:42 
 * @history:
 */
public interface IUserAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    /**
     * 检查手机号是否存在
     * @param mobile
     * @param kind
     * @param systemCode 
     * @create: 2016年12月14日 下午7:25:30 xieyj
     * @history:
     */
    public void doCheckMobile(String mobile, String kind, String systemCode);

    /**
     * 检查手机号是否存在
     * @param mobile
     * @param kind
     * @param companyCode
     * @param systemCode 
     * @create: 2016年12月14日 下午5:45:18 xieyj
     * @history:
     */
    public void doCheckMobile(String mobile, String kind, String companyCode,
            String systemCode);

    /**
     * 检查登录密码是否正确
     * @param userId
     * @param password 
     * @create: 2017年2月12日 下午10:00:14 xieyj
     * @history:
     */
    public void doCheckLoginPwd(String userId, String password);

    /**
     * 注册
     * @param mobile
     * @param loginPwd
     * @param loginPwdStrength
     * @param userReferee
     * @param smsCaptcha
     * @param kind
     * @param isRegHx
     * @param province
     * @param city
     * @param area
     * @param systemCode
     * @return 
     * @create: 2017年1月7日 下午12:57:42 xieyj
     * @history:
     */
    public String doRegister(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String kind, String isRegHx, String province, String city,
            String area, String systemCode);

    /**
     * 注册分配账户送积分
     * @param mobile
     * @param loginPwd
     * @param loginPwdStrength
     * @param userReferee
     * @param smsCaptcha
     * @param systemCode
     * @return 
     * @create: 2016年12月14日 下午5:54:26 xieyj
     * @history:
     */
    public XN805154Res doRegisterAddJf(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String systemCode);

    /**
     * 注册送积分，不分配账户
     * @param mobile
     * @param loginPwd
     * @param loginPwdStrength
     * @param userReferee
     * @param smsCaptcha
     * @param companyCode
     * @param isMall 1 区分商户手机号
     * @return 
     * @create: 2016年11月24日 上午11:07:45 xieyj
     * @history:
     */
    public String doRegisterSingle(String mobile, String loginPwd,
            String loginPwdStrength, String userReferee, String smsCaptcha,
            String companyCode, String isMall, String systemCode);

    /**
     * 代注册
     * @param loginName
     * @param mobile
     * @param idKind
     * @param idNo
     * @param realName
     * @param userReferee
     * @param updater
     * @param remark
     * @param kind
     * @param pdf
     * @param roleCode
     * @param province
     * @param city
     * @param area
     * @return 
     * @create: 2017年1月7日 下午1:03:56 xieyj
     * @history:
     */
    public String doAddUser(String loginName, String mobile, String idKind,
            String idNo, String realName, String userReferee, String updater,
            String remark, String kind, String pdf, String roleCode,
            String province, String city, String area, String systemCode);

    public String doAddUser(String mobile, String realName, String userReferee,
            String updater, String remark, String kind, String systemCode);

    /**
     * 城市网代注册
     * @param mobile
     * @param companyCode
     * @param userReferee
     * @param systemCode
     * @return 
     * @create: 2016年12月14日 下午5:57:03 xieyj
     * @history:
     */
    public String doAddUser(String mobile, String companyCode,
            String userReferee, String systemCode);

    /**
     * 微信等第三方注册
     * @param openId
     * @param nickName
     * @param photo
     * @param gender
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2016年12月14日 下午5:57:25 xieyj
     * @history:
     */
    public String doThirdRegister(String openId, String nickName, String photo,
            String gender, String companyCode, String systemCode);

    public String doThirdRegisterWechat(String openId, String nickName,
            String photo, String gender, String companyCode, String systemCode);

    /**
     * 新增合伙人(正汇)
     * @param user
     * @param province
     * @param city
     * @param area
     * @return 
     * @create: 2016年12月27日 下午4:28:14 xieyj
     * @history:
     */
    public String doAddPartner(User user, String province, String city,
            String area);

    /**
     * 修改合伙人(正汇)
     * @param user
     * @param province
     * @param city
     * @param area 
     * @create: 2016年12月27日 下午4:28:33 xieyj
     * @history:
     */
    public void doEditPartner(User user, String province, String city,
            String area);

    /**
     * 用户登录
     * @param loginName
     * @param loginPwd
     * @param kind
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2016年12月14日 下午5:57:42 xieyj
     * @history:
     */
    public String doLogin(String loginName, String loginPwd, String kind,
            String companyCode, String systemCode);

    /**
     * 微信登录
     * @param code 微信提供
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2016年12月27日 下午5:38:18 haiqingzheng
     * @history:
     */
    public String doLoginWeChat(String code, String companyCode,
            String systemCode);

    /**
     * 小程序登录
     * @param jsCode
     * @param nickname
     * @param sex
     * @param headimgurl
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2017年1月19日 下午4:46:06 xieyj
     * @history:
     */
    public String doLoginSmallWeChat(String jsCode, String nickname,
            String sex, String headimgurl, String companyCode, String systemCode);

    /**
     * 用户登录送积分
     * @param loginName
     * @param loginPwd
     * @param kind
     * @param systemCode
     * @return 
     * @create: 2016年12月14日 下午5:58:16 xieyj
     * @history:
     */
    public XN805155Res doLoginAddJf(String loginName, String loginPwd,
            String kind, String systemCode);

    /**
     * 实名认证
     * @param userId
     * @param idKind
     * @param idNo
     * @param realName 
     * @create: 2016年5月24日 下午5:10:00 myb858
     * @history:
     */
    public void doIdentify(String userId, String idKind, String idNo,
            String realName);

    /**
     * 四要素实名认证
     * @param userId
     * @param idKind
     * @param idNo
     * @param realName
     * @param cardNo
     * @param bindMobile 
     * @create: 2017年1月4日 上午10:56:27 xieyj
     * @history:
     */
    public void doFourIdentify(String userId, String idKind, String idNo,
            String realName, String cardNo, String bindMobile);

    /**
     * 修改真实姓名
     * @param userId
     * @param realName 
     * @create: 2016年6月1日 下午12:18:03 xieyj
     * @history:
     */
    public void doEditRealName(String userId, String realName);

    /**
     * 设置支付密码
     * @param userId
     * @param tradePwd
     * @param tradePwdStrength
     * @param smsCaptcha 
     * @create: 2016年5月24日 下午5:16:06 myb858
     * @history:
     */
    public void doSetTradePwd(String userId, String tradePwd,
            String tradePwdStrength, String smsCaptcha);

    /**
     * 实名认证+支付密码设置
     * @param userId
     * @param idKind
     * @param idNo
     * @param realName
     * @param tradePwd
     * @param tradePwdStrength
     * @param smsCaptcha 
     * @create: 2016年5月24日 下午5:21:15 myb858
     * @history:
     */
    public void doIdentifySetTradePwd(String userId, String idKind,
            String idNo, String realName, String tradePwd,
            String tradePwdStrength, String smsCaptcha);

    /**
     * 更换手机号
     * @param userId
     * @param newMobile
     * @param smsCaptcha
     * @param tradePwd
     * @param isMall 1 区分商户
     * @create: 2016年11月24日 上午11:13:37 xieyj
     * @history:
     */
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd, String isMall);

    /**
     * 绑定手机号
     * @param userId
     * @param mobile
     * @param smsCaptcha
     * @param companyCode
     * @create: 2016年12月14日 下午5:58:51 xieyj
     * @history:
     */
    public void doBindMoblie(String userId, String mobile, String smsCaptcha,
            String companyCode);

    /**
     * 找回登录密码
     * @param mobile
     * @param smsCaptcha
     * @param newLoginPwd
     * @param loginPwdStrength
     * @param kind
     * @param systemCode 
     * @create: 2016年12月14日 下午9:05:18 xieyj
     * @history:
     */
    public void doFindLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd, String loginPwdStrength, String kind,
            String systemCode);

    /**
     * 找回登录密码
     * @param mobile
     * @param smsCaptcha
     * @param newLoginPwd
     * @param loginPwdStrength
     * @param kind
     * @param companyCode
     * @param systemCode 
     * @create: 2016年12月14日 下午9:05:24 xieyj
     * @history:
     */
    public void doFindLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd, String loginPwdStrength, String kind,
            String companyCode, String systemCode);

    /**
     * 管理端找回登录密码
     * @param loginName
     * @param smsCaptcha
     * @param newLoginPwd
     * @param loginPwdStrength
     * @param systemCode 
     * @create: 2016年12月14日 下午5:59:58 xieyj
     * @history:
     */
    public void doFindLoginPwdByOss(String loginName, String smsCaptcha,
            String newLoginPwd, String loginPwdStrength, String systemCode);

    /**
     * admin管理员重置密码
     * @param userId
     * @param adminUserId
     * @param adminPwd 
     * @create: 2016年12月14日 下午9:51:15 xieyj
     * @history:
     */
    public void doFindLoginPwdByOss(String userId, String adminUserId,
            String adminPwd);

    /**
     * 重置登录密码
     * @param userId
     * @param oldLoginPwd
     * @param newLoginPwd
     * @param loginPwdStrength
     * @return 
     * @create: 2016年5月24日 下午7:05:18 myb858
     * @history:
     */
    public void doResetLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd, String loginPwdStrength);

    /**
     * 找回支付密码
     * @param userId
     * @param newTradePwd
     * @param tradePwdStrength
     * @param smsCaptcha
     * @param idKind
     * @param idNo
     * @return 
     * @create: 2016年5月24日 下午5:30:42 myb858
     * @history:
     */
    public void doFindTradePwd(String userId, String newTradePwd,
            String tradePwdStrength, String smsCaptcha, String idKind,
            String idNo);

    /**
     * 找回支付密码(无需实名认证)
     * @param userId
     * @param newTradePwd
     * @param tradePwdStrength
     * @param smsCaptcha
     * @param idKind
     * @param idNo
     * @return 
     * @create: 2016年5月24日 下午5:30:42 myb858
     * @history:
     */
    public void doFindTradePwd(String userId, String newTradePwd,
            String tradePwdStrength, String smsCaptcha);

    /**
     * 重置支付密码
     * @param userId
     * @param oldTradePwd
     * @param newTradePwd
     * @param tradePwdStrength
     * @return 
     * @create: 2016年5月24日 下午7:05:39 myb858
     * @history:
     */
    public void doResetTradePwd(String userId, String oldTradePwd,
            String newTradePwd, String tradePwdStrength);

    /**
     * 注销/激活用户
     * @param userId
     * @param toStatus
     * @param updater
     * @param remark
     * @return 
     * @create: 2016年5月24日 下午5:46:50 myb858
     * @history:
     */
    public void doStatusUser(String userId, String toStatus, String updater,
            String remark);

    /**
     * 设置角色
     * @param userId
     * @param roleCode
     * @param updater
     * @param remark
     * @return 
     * @create: 2016年5月24日 下午5:50:16 myb858
     * @history:
     */
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark);

    /**
     * 完善用户信息
     * @param userId
     * @param pdf
     * @param updater
     * @param remark 
     * @create: 2016年8月31日 上午9:22:11 xieyj
     * @history:
     */
    public void doPdfUser(String userId, String pdf, String updater,
            String remark);

    /** 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-6-7 上午10:04:12 miyb
     * @history: 
     */
    public Paginable<User> queryUserPage(int start, int limit, User condition);

    /**
     * 
     * @param condition
     * @return 
     * @create: 2015年10月28日 下午2:41:48 myb858
     * @history:
     */
    public List<User> queryUserList(User condition);

    /**
     * @param userId
     * @return 
     * @create: 2016年12月15日 下午8:52:16 xieyj
     * @history:
     */
    public List<User> getUserRefereeList(String userId);

    /**
     * 查询用户的详细信息
     * @param userId
     * @create: 2014-12-10 下午7:37:18 miyb
     * @history:
     */
    public User doGetUser(String userId);

    /**
     * 根据手机号，种类，公司编号，系统编号获取用户编号
     * @param mobile
     * @param kind
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2016年12月28日 上午10:38:08 xieyj
     * @history:
     */
    public String doGetUserIdByCondition(String mobile, String kind,
            String companyCode, String systemCode);

    /**
     * 根据loginName获取手机号，发送找回登录密码短信验证码
     * @param loginName 
     * @create: 2016年8月16日 下午3:47:12 xieyj
     * @history:
     */
    public void doSendLoginPwdSms(String loginName, String systemCode);

    /**
     * 校验支付密码
     * @param userId
     * @param tradePwd
     * @return 
     * @create: 2015年11月10日 上午9:16:42 myb858
     * @history:
     */
    public void checkTradePwd(String userId, String tradePwd);

    /**
     * 发送短信
     * @param userId
     * @param content 
     * @create: 2016年5月24日 下午6:46:07 myb858
     * @history:
     */
    public void sendAppSms(String userId, String content);

    /**
     * 更改用户名
     * @param userId
     * @param loginName 
     * @create: 2016年10月22日 下午12:04:40 xieyj
     * @history:
     */
    public void editLoginName(String userId, String loginName);

    /** 
     * 更改昵称
     * @param userId
     * @param nickname 
     * @create: 2016年9月18日 下午5:27:53 zuixian
     * @history: 
     */
    public void editNickname(String userId, String nickname);

    /**
     * 单人划转加积分
     * @param userId
     * @param direction
     * @param amount
     * @param remark
     * @param refNo 
     * @create: 2016年10月11日 下午8:07:17 xieyj
     * @history:
     */
    public void doTransfer(String userId, String direction, Long amount,
            String remark, String refNo);

    /**
     * 活动加积分
     * @param userId
     * @param direction
     * @param ruleType
     * @param refNo 
     * @create: 2016年10月23日 下午8:48:34 xieyj
     * @history:
     */
    public void doTransfer(String userId, String direction, String ruleType,
            String refNo);

    /**
     * 单向划转加钱
     * @param fromUser
     * @param toUser
     * @param amount
     * @param remark
     * @param refNo 
     * @create: 2016年10月24日 下午11:06:02 xieyj
     * @history:
     */
    public void doTransferAdd(String fromUser, String toUser, Long amount,
            String remark, String refNo);

    /**
     * 补充用户信息
     * @param data 
     * @create: 2016年12月28日 下午5:00:58 xieyj
     * @history:
     */
    public void doSuppleUser(User data);

    /**
     * 验证码登录注册
     * @param mobile
     * @param kind
     * @param smsCaptcha
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2017年1月19日 下午5:48:21 xieyj
     * @history:
     */
    public String doCaptchaLoginReg(String mobile, String kind,
            String smsCaptcha, String companyCode, String systemCode);
}
