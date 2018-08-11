package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InnerSpecs;

/**
 * 内购产品规格
 * @author: LENOVO 
 * @since: 2018年8月1日 下午4:22:20 OOOOoo
 * @history:
 */
public interface IInnerSpecsBO extends IPaginableBO<InnerSpecs> {

    void saveInnerSpecs(String code, List<InnerSpecs> specsList);

    void removeInnerSpecs(InnerSpecs data);

    InnerSpecs getInnerSpecs(String code);

    List<InnerSpecs> getInnerSpecsByProduct(String code);

    List<InnerSpecs> queryInnerSpecsList(InnerSpecs psCondition);

    void saveInnerSpecs(String code, InnerSpecs innerSpecs);

    void refreshInnerSpecs(InnerSpecs innerSpecs);

}
