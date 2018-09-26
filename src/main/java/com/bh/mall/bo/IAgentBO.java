package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.SqForm;

public interface IAgentBO extends IPaginableBO<Agent> {

    // 前端用户注册
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String loginPwd, String nickname, String photo,
            String status, String fromUserId);

    // 申请意向代理
    public void applyAgent(Agent agent, String realName, String wxId,
            String mobile, String province, String city, String area,
            String address, String logCode);

    // 微信登录
    public Agent doGetUserByOpenId(String h5OpenId);

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile);

    public Agent getAgent(String userId);

    /*************** 获取数据库信息 **********************/
    // 获取数据库user信息
    public List<Agent> queryAgentList(Agent condition);

    // 根据身份号获取用户信息
    public Agent getAgentByIdNo(String idNo);

    public void checkTeamName(String teamName);

    public List<Agent> getAgentByUserReferee(String userReferee);

    public void refreshStatus(Agent data, String updater, String remark);

    public Agent getAgentByMobile(String introducer);

    /*************** 信息更新 **********************/

    public void refreshReferee(Agent data, String userReferee, String updater,
            String remark);

    public void refreshPhoto(Agent buser, String photo);

    public void refreshAgent(Agent data, String wxId, String mobile,
            String realName, String teamName, String province, String city,
            String area, String address);

    public void resetBindMobile(Agent buser, String newMobile);

    public void refreshHighUser(Agent data, String highUser, String updater,
            String remark);

    public void refreshManager(Agent data, String manager, String updater);

    public void refreshLevel(Agent buser);

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

    // 清空信息
    public void resetInfo(Agent agent);

    // 跟新搭理
    public void refreshAgent(SqForm sqForm, String logCode, String status);

    // 清空推荐关系
    public void resetUserReferee(Agent data, String referrer);

    // 最后代理记录
    public void refreshLog(Agent agent, String logCode);

    // 填写授权资料
    public void addInfo(SqForm sqForm, String logCode, String status);

    public void refreshSq(Agent data, SqForm sqForm, String manager,
            String highUserId, String teamName, Integer level, String status,
            String approver, String approveName, String logCode, Date date);

    public void refreshYx(Agent agent, String status, String approver,
            String approveName, String logCode);

    public void refreshSj(Agent agent, SjForm sjForm, String approver,
            String approveName, String remark, String status, String logCode);

    public void refreshStatus(Agent dbUser, String status);

    public void refreshIsImpower(Agent agent, String isImpower);

    public void doSetTrader(Agent data, String updater, String remark);

    public void doCancelTrader(Agent data, String updater, String remark);

}
