package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InnerSpecs;

/**
 * 内购产品规格
 * @author: LENOVO 
 * @since: 2018年8月1日 下午4:22:20 
 * @history:
 */
public interface IInnerSpecsBO extends IPaginableBO<InnerSpecs> {

    public boolean isInnerSpecsExist(String code);

    public int saveInnerSpecs(InnerSpecs data);

    public int removeInnerSpecs(String code);

    public int refreshInnerSpecs(InnerSpecs data);

    public InnerSpecs getInnerSpecs(String code);

    public List<InnerSpecs> queryInnerSpecsList(InnerSpecs data);
}