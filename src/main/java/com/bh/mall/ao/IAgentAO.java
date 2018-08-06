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

    // B端微信注册/登录 627310
    public XN627303Res doLoginWeChatByAgent(String code, String fromUserId);

    // 修改头像 627316
    public void doModifyPhoto(String userId, String photo);

    // 修改资料 627314
    public void editInformation(String userId, String wxId, String mobile,
            String realName, String teamName, String province, String city,
            String area, String address);

    // TODO 修改交易密码 627315

    // 更换手机 627311
    public void doResetMoblie(String userId, String newMobile,
            String smsCaptcha);

    // 修改上级 627312
    public void editHighUser(String userId, String highUser, String updater);

    // 修改推荐人 627313
    public void editUserReferee(String userId, String userReferee,
            String updater);

    // 修改管理员 627317
    public void editManager(String userId, String manager, String updater);

    // 分页查询代理 627325
    public Paginable<Agent> queryAgentPage(int start, int limit,
            Agent condition);

    // 列表查询代理 627326
    public List<Agent> queryAgentList(Agent condition);

    // 详细查询 627327
    public Agent getAgent(String userId);

    // 详细查询包含代理轨迹 TODO 627328
    // public Agent getAgent(String userId);

    // 分页我的下级 627320
    public Paginable<Agent> queryMyLowAgentPage(int start, int limit,
            Agent condition);

    // 列表查询代理结构 627321
    public List<Agent> queryAgentJgList(Agent condition);

    // 公司直接取消授权 TODO
    public void abolishSqForm(String userId, String updater, String remark);

    // 根据手机号查询代理 627322
    public Agent doGetAgentByMobile(String mobile);

}
