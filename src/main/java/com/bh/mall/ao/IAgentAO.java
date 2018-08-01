package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.dto.res.XN627303Res;

/**
 * B端代理用户
 * @author: clockorange 
 * @since: Jul 6, 2018 4:29:55 AM 
 * @history:
 */
public interface IAgentAO {

    String DEFAULT_ORDER_COLUMN = "user_id";

    // B端微信注册/登录
    public XN627303Res doLoginWeChatByAgent(String code, String userReferee);

    // 修改头像
    public void doModifyPhoto(String userId, String photo);

    // 更换手机
    public void doResetMoblie(String userId, String newMobile,
            String smsCaptcha);

    // 修改上级
    public void editHighUser(String userId, String highUser, String updater);

    // 修改推荐人
    public void editUserReferee(String userId, String userReferee,
            String updater);

    // 修改管理员
    public void editManager(String userId, String manager, String updater);

    // 我的下级

    // 列表查询代理
    public List<Agent> queryAgentList(Agent condition);

    // 分页查询代理
    public Paginable<Agent> queryAgentPage(int start, int limit,
            Agent condition);

<<<<<<< HEAD
    // 列表查询代理结构
    public List<Agent> queryAgentJgList(Agent condition);
=======
    // 分页查询代理结构
    public Paginable<Agent> queryMyLowAgentPage(int start, int limit,
            Agent condition);

    // 列表查询代理结构
    public List<Agent> queryAgentList(Agent condition);

    // 列表查询代理轨迹
    public List<Agent> getAgentLog(Agent condition);
>>>>>>> refs/remotes/origin/master

    // 公司直接取消授权
    public void abolishSqForm(String userId, String updater, String remark);

    // 根据手机号查询代理
    public Agent doGetAgentByMobile(String mobile);

    // 详细查询
    public Agent getAgent(String userId);

}
