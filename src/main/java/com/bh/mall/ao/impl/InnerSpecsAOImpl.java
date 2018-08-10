package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.exception.BizException;

@Service
public class InnerSpecsAOImpl implements IInnerSpecsAO {

    @Autowired
    IInnerSpecsBO innerSpecsBO;

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
