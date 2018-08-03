package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;

public interface IAgentBO extends IPaginableBO<Agent> {

    // 前端用户注册
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String loginPwd, String nickname, String photo,
            String status, Integer level, String fromUserId);

    // 微信登录
    public Agent doGetUserByOpenId(String h5OpenId);

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile);

    // 判断登录名是否存在
    public void isLoginNameExist(String loginName, String kind);

    public String getUserId(String mobile, String kind);

    // 查询openId
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd, String alertStr);

    // 校验是否已经有人实名认证
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName);

    public Agent getAgent(String userId);

    /*************** 获取数据库信息 **********************/
    // 获取数据库user信息
    public List<Agent> queryAgentList(Agent condition);

    // 根据身份号获取用户信息
    public Agent getAgentByIdNo(String idNo);

    public void checkTeamName(String teamName);

    // 判断推荐人是否存在(手机号)
    public void checkAgentReferee(String userReferee, String systemCode);

    public List<Agent> queryAgentList(String mobile, String kind);

    public List<Agent> getAgentByUserReferee(String userReferee);

    public Agent getAgentByLoginName(String loginName, String systemCode);

    public void refreshStatus(Agent data, String updater, String remark);

    public Agent getAgentByMobile(String introducer);

    /*************** 信息更新 **********************/
    // 保存， 更新
    public void refreshWxInfo(String userId, String type, String unionId,
            String openId, String nickname, String photo);

    public String saveUser(String mobile, String kind);

    // 更新
    public void resetLoginPwd(Agent buser, String loginPwd);

    public void refreshLoginName(String userId, String loginName);

    public void refreshNickname(String userId, String nickname);

    public void refreshReferee(Agent data, String userReferee, String updater);

    public void refreshPhoto(String userId, String photo);

    public void refreshAgent(Agent data);

    public void resetBindMobile(Agent buser, String newMobile);

    public void refreshHighUser(Agent data, String highUser, String updater);

    public void refreshManager(Agent data, String manager, String updater);

    /*************** 查询 **********************/

    public List<Agent> selectList(Agent condition, int pageNo, int pageSize);

    public List<Agent> selectAgentFront(Agent condition, int pageNo,
            int pageSize);

    public Agent getAgentName(String userReferee);

    public void checkTradePwd(String userId, String tradePwd);

    public Agent getTeamLeader(String teamName);

    // 是否为最高等级代理
    public boolean isHighest(String userId);

    public void refreshInfo(Agent applyAgent);

}
