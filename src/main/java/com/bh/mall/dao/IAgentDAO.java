package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Agent;

public interface IAgentDAO extends IBaseDAO<Agent> {

    String NAMESPACE = IAgentDAO.class.getName().concat(".");

    /**
     * 设置登录密码 
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:06:51 LENOVO
     * @history:
     */
    public int updateLoginPwd(Agent data);

    /**
     * 更新状态 
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:07:13 LENOVO
     * @history:
     */
    public int updateStatus(Agent data);

    /**
     * 更新头像 
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:08:33 LENOVO
     * @history:
     */
    public int updatePhoto(Agent data);

    /**
     * 更新
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:08:57 LENOVO
     * @history:
     */
    public int update(Agent data);

    /**
     * 更新等级
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:09:56 LENOVO
     * @history:
     */
    public int updateLevel(Agent data);

    /**
     * 更新用户手机号和真实信息 
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:10:11 LENOVO
     * @history:
     */
    public int updateMobileIds(Agent data);

    /**
     * 微信登录更新用户信息 
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:10:20 LENOVO
     * @history:
     */
    public int updateWxInfo(Agent data);

    /**
     * 重置号码
     * @param user 
     * @create: 2018年7月31日 下午2:12:58 LENOVO
     * @history:
     */
    public void resetBindMobile(Agent user);

    public void refreshLevel(Agent user);

    /**
     * 查询下级代理 
     * @param condition
     * @param start
     * @param limit
     * @return 
     * @create: 2018年7月31日 下午2:14:41 LENOVO
     * @history:
     */
    public List<Agent> selectAgentFront(Agent condition, int start, int limit);

    /**
     * 修改推荐的代理的上级 
     * @param data 
     * @create: 2018年7月31日 下午2:14:52 LENOVO
     * @history:
     */
    public void updateHigh(Agent data);

    public void updateUserReferee(Agent data);

    /**
     * 更新关联管理员
     * @param data 
     * @create: 2018年7月31日 下午2:16:52 LENOVO
     * @history:
     */
    public void updateManager(Agent data);

    public void updateInfo(Agent data);

    public void updateTeamName(Agent data);

    // 申请意向代理
    public void applyAgent(Agent data);

    // 更新最后一条代理轨迹
    public void updateLastLog(Agent data);

    // 清空手机号等信息
    public void resetInfo(Agent data);

    // 清空推荐关系
    public void resetUserReferee(Agent agent);

    // 添加授权资料
    public void addInfo(Agent data);

    public void updateSq(Agent data);

    public void updateSj(Agent data);

}
