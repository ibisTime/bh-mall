package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627547Req;
import com.bh.mall.dto.req.XN627890Req;

/**
 * 内购产品规格
 * @author: LENOVO 
 * @since: 2018年8月1日 下午4:22:20 
 * @history:
 */
public interface IInnerSpecsBO extends IPaginableBO<InnerSpecs> {

    public boolean isInnerSpecsExist(String code);

    public void saveInnerSpecsList(String code, List<XN627890Req> specList,
            String updater);

    public void saveInnerSpecs(String code, XN627890Req innerpsReq);

    public void removeInnerSpecs(InnerSpecs data);

    public void refreshInnerSpecs(XN627890Req innerpsReq,
            List<XN627547Req> innerSpecsPriceList);

    public InnerSpecs getInnerSpecs(String code);

    public List<InnerSpecs> queryInnerSpecsList(InnerSpecs data);
}
