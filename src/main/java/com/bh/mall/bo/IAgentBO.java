package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.SqForm;

public interface IAgentBO extends IPaginableBO<Agent> {

    // 前端用户注册
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String loginPwd, String nickname, String photo,
            String status, Integer level, String fromUserId);

    // 申请意向代理
    public void applyAgent(Agent agent, String realName, String wxId,
            String mobile, String province, String city, String area,
            String address, String logCode);

    // 更新最后一条代理日志
    public void refreshLastLog(Agent agent, String status, String approver,
            String approveName, String logCode);

    // 微信登录
    public Agent doGetUserByOpenId(String h5OpenId);

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile);

    public String getUserId(String mobile, String kind);

    // 查询openId
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId);

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

    public void refreshStatus(Agent data, String updater, String remark);

    public Agent getAgentByMobile(String introducer);

    /*************** 信息更新 **********************/
    // 保存， 更新
    public void refreshWxInfo(String userId, String type, String unionId,
            String openId, String nickname, String photo);

    public void refreshReferee(Agent data, String userReferee, String updater);

    public void refreshPhoto(String userId, String photo);

    public void refreshAgent(Agent data);

    public void resetBindMobile(Agent buser, String newMobile);

    public void refreshHighUser(Agent data, String highUser, String updater);

    public void refreshManager(Agent data, String manager, String updater);

    /*************** 查询 **********************/

    public List<Agent> selectList(Agent condition, int pageNo, int pageSize);

    public String getAgentName(String userReferee);

    public void checkTradePwd(String userId, String tradePwd);

    public Agent getTeamLeader(String teamName);

    // 是否为最高等级代理
    public boolean isHighest(String userId);

    public void refreshInfo(Agent applyAgent);

    // 修改团队名称
    public void refreshTeamName(Agent data, String teamName);

    public void refreshAgent(SqForm sqForm);

    // 清空信息
    public void resetInfo(Agent agent);

    // 授权成功
    public void sqSuccess(SqForm sqForm);

    // 升级成功
    public void sjSuccess(SjForm sjForm);

    public void refreshAgent(SqForm sqForm, String logCode);

    // 清空推荐关系
    public void resetUserReferee(String userId);

}
