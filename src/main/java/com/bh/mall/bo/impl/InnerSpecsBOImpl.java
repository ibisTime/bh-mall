package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IInnerSpecsDAO;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.exception.BizException;

@Component
public class InnerSpecsBOImpl extends PaginableBOImpl<InnerSpecs>
        implements IInnerSpecsBO {

    @Autowired
    private IInnerSpecsDAO innerSpecsDAO;

    @Override
    public void saveInnerSpecs(String innerProductCode,
            List<InnerSpecs> specsList) {
        for (InnerSpecs data : specsList) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.InnerOrder.getCode());
            data.setCode(code);
            data.setInnerProductCode(innerProductCode);
            innerSpecsDAO.insert(data);
        }
    }

    @Override
    public void removeInnerSpecs(InnerSpecs data) {
        innerSpecsDAO.delete(data);
    }

    @Override
    public InnerSpecs getInnerSpecs(String code) {
        InnerSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            InnerSpecs condition = new InnerSpecs();
            condition.setCode(code);
            data = innerSpecsDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "产品规格不存在");
            }
        }
        return data;
    }

    @Override
    public List<InnerSpecs> getInnerSpecsByProduct(String innerProductCode) {
        InnerSpecs condition = new InnerSpecs();
        condition.setInnerProductCode(innerProductCode);
        return innerSpecsDAO.selectList(condition);
    }

    @Override
    public List<InnerSpecs> queryInnerSpecsList(InnerSpecs condition) {
        return innerSpecsDAO.selectList(condition);
    }

    @Override
    public void saveInnerSpecs(String innerProductCode, InnerSpecs data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.InnerOrder.getCode());
        data.setCode(code);
        data.setInnerProductCode(innerProductCode);
        innerSpecsDAO.insert(data);
    }

    @Override
    public void refreshInnerSpecs(InnerSpecs data) {
        innerSpecsDAO.update(data);
    }

}
