package com.xn.sdhh.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xn.sdhh.bo.IBusinessBO;
import com.xn.sdhh.bo.base.PaginableBOImpl;
import com.xn.sdhh.dao.IBusinessDAO;
import com.xn.sdhh.domain.Business;
import com.xn.sdhh.exception.BizException;

@Component
public class BusinessBOImpl extends PaginableBOImpl<Business>
        implements IBusinessBO {

    @Autowired
    IBusinessDAO businessDAO;

    @Override
    public String saveBusiness() {
        return null;
    }

    @Override
    public void refreshCashier(Business data) {
        businessDAO.update(data);
    }

    @Override
    public void refreshAccountant(Business data) {
        businessDAO.update(data);
    }

    @Override
    public void refreshLoan(Business data) {
        businessDAO.update(data);
    }

    @Override
    public List<Business> queryBusinessList(Business condition) {
        return businessDAO.selectList(condition);
    }

    @Override
    public Business getBusiness(String code) {
        Business data = null;
        if (StringUtils.isNotBlank(code)) {
            Business condition = new Business();
            condition.setCode(code);
            data = businessDAO.select(condition);
            if (null == data) {
                throw new BizException("xn00000", "业务:" + code + "不存在");
            }
        }
        return data;
    }

}
