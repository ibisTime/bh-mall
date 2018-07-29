package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Agent;

public interface IAgentDAO extends IBaseDAO<Agent> {

    String NAMESPACE = IAgentDAO.class.getName().concat(".");

    // 设置登录密码
    public int updateLoginPwd(Agent data);

    // 更新状态
    public int updateStatus(Agent data);

    // 更新角色
    public int updateRole(Agent data);

    // 更新用户名
    public int updateLoginName(Agent data);

    // 更新昵称
    public int updateNickname(Agent data);

    // 更新头像
    public int updatePhoto(Agent data);

    public int update(Agent data);

    public int updateLevel(Agent data);

    // 更新用户手机号和真实信息
    public int updateMobileIds(Agent data);

    // 微信登录更新用户信息
    public int updateWxInfo(Agent data);

    public int approveUser(Agent data);

    public void resetBindMobile(Agent user);

    public void updateInformation(Agent data);

    public void updateLog(Agent data);

    // 查询下级代理
    public List<Agent> selectAgentFront(Agent condition, int start, int limit);

    // 修改推荐的代理的上级
    public void updateHigh(Agent data);

    public void updateManager(Agent data);

}
