package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IInnerSpecsDAO;
import com.bh.mall.domain.InnerSpecs;

@Component
public class InnerSpecsBOImpl extends PaginableBOImpl<InnerSpecs>
        implements IInnerSpecsBO {

    @Autowired
    private IInnerSpecsDAO innerSpecsDAO;

    @Override
    public boolean isInnerSpecsExist(String code) {
        InnerSpecs innerSpecs = new InnerSpecs();
        innerSpecs.setCode(code);
        if (innerSpecsDAO.selectTotalCount(innerSpecs) >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public int saveInnerSpecs(InnerSpecs data) {
        int count = 0;
        count = innerSpecsDAO.insert(data);
        return count;
    }

    @Override
    public int removeInnerSpecs(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            InnerSpecs data = new InnerSpecs();
            data.setCode(code);
            count = innerSpecsDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshInnerSpecs(InnerSpecs data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            count = innerSpecsDAO.update(data);
        }
        return count;
    }

    @Override
    public List<InnerSpecs> queryInnerSpecsList(InnerSpecs data) {
        return innerSpecsDAO.selectList(data);
    }

    @Override
    public InnerSpecs getInnerSpecs(String code) {
        InnerSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            InnerSpecs condition = new InnerSpecs();
            condition.setCode(code);
            data = innerSpecsDAO.select(condition);
        }
        return data;
    }
}
