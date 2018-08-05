package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.JsAward;
import com.bh.mall.dto.req.XN627242Req;

/**
 * 介绍奖
 * @author: LENOVO 
 * @since: 2018年8月1日 上午9:40:20 
 * @history:
 */
@Component
public interface IJsAwardAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增介绍奖励
    public String addJsAward(String level, String jsAwardLevel, String percent,
            String updater, String remark);

    // 删除介绍奖励
    public void dropJsAward(String code);

    // 奖修改介绍奖励
    public void editJsAward(XN627242Req req);

    // 分页查询介绍奖励
    public Paginable<JsAward> queryJsAwardPage(int start, int limit,
            JsAward condition);

    // 列表查询介绍奖励
    public List<JsAward> queryJsAwardList(JsAward condition);

    // 详情查询介绍奖励
    public JsAward getJsAward(String code);

}
