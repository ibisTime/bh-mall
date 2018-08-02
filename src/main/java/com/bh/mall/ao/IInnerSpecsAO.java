package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627900Req;

/**
 * 内购产品规格
 * @author: LENOVO 
 * @since: 2018年8月1日 下午4:33:32 
 * @history:
 */
public interface IInnerSpecsAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 新增内购产品规格
    public String addInnerSpecs(XN627900Req req);

    // 删除内购产品规格
    public boolean dropInnerSpecs(String code);

    // 修改内购产品规格
    public boolean editInnerSpecs(InnerSpecs data);

    // 列表查询内购产品规格（根据内购产品编号）
    public List<InnerSpecs> queryInnerSpecsList(InnerSpecs condition);

    // 分页查询内购产品规格
    public Paginable<InnerSpecs> queryInnerSpecsPage(int start, int limit,
            InnerSpecs condition);

    // 详细查询内购产品规格
    public InnerSpecs getInnerSpecs(String code);

}
