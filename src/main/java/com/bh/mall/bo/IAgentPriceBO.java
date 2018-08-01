package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.dto.req.XN627547Req;

/**
 * 产品规格定价表
 * @author: LENOVO 
 * @since: 2018年8月1日 下午2:10:31 
 * @history:
 */
public interface IAgentPriceBO extends IPaginableBO<AgentPrice> {

    // 根据编号查询是否存在
    public boolean isAgentPriceExist(String code);

    // 新增
    public String saveAgentPrice(AgentPrice data);

    // 删除
    public int removeAgentPrice(String code);

    // 更新
    public void refreshAgentPrice(List<XN627547Req> list);

    // 列表查询
    public List<AgentPrice> queryAgentPriceList(AgentPrice pspConditon);

    // 详情查询
    public AgentPrice getAgentPrice(String code);

    // 根据等级查询
    public AgentPrice getPriceByLevel(String specsCode, Integer level);

    // 根据规格编号
    public List<AgentPrice> getAgentPriceBySpecsCode(String specsCode);

    // 检查机构数量
    public int checkMinQuantity(String productSpecsCode, Integer level);

}
