package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627900Req;
import com.bh.mall.exception.BizException;

@Service
public class InnerSpecsAOImpl implements IInnerSpecsAO {

    @Autowired
    IInnerSpecsBO innerSpecsBO;

    @Override
    public String addInnerSpecs(XN627900Req req) {
        String code = OrderNoGenerater.generate("IS");
        InnerSpecs data = new InnerSpecs();

        data.setCode(code);
        data.setInnerProductCode(req.getInnerProductCode());
        data.setName(req.getName());
        data.setNumber(Integer.valueOf(req.getNumber()));
        data.setWeight(Integer.valueOf(req.getWeight()));

        data.setPrice(Integer.valueOf(req.getWeight()));
        data.setRefCode(req.getRefCode());
        data.setIsSingle(req.getIsSingle());
        data.setSingleNumber(Integer.valueOf(req.getSingleNumber()));

        innerSpecsBO.saveInnerSpecs(data);
        return code;
    }

    @Override
    @Transactional
    public boolean dropInnerSpecs(String code) {
        if (!innerSpecsBO.isInnerSpecsExist(code)) {
            throw new BizException("lh4000", "内购产品规格编号不存在！");
        }
        innerSpecsBO.removeInnerSpecs(code);
        return true;

    }

    @Override
    public boolean editInnerSpecs(InnerSpecs data) {
        if (data != null && innerSpecsBO.isInnerSpecsExist(data.getCode())) {
            innerSpecsBO.refreshInnerSpecs(data);
        } else {
            throw new BizException("lh4000", "内购产品规格编号不存在！");
        }
        return true;
    }

    @Override
    public List<InnerSpecs> queryInnerSpecsList(InnerSpecs condition) {
        return innerSpecsBO.queryInnerSpecsList(condition);
    }

    @Override
    public Paginable<InnerSpecs> queryInnerSpecsPage(int start, int limit,
            InnerSpecs condition) {
        return innerSpecsBO.getPaginable(start, limit, condition);
    }

    @Override
    public InnerSpecs getInnerSpecs(String code) {
        if (!innerSpecsBO.isInnerSpecsExist(code)) {
            throw new BizException("lh4000", "内购产品规格编号不存在！");
        }
        return innerSpecsBO.getInnerSpecs(code);
    }
}
