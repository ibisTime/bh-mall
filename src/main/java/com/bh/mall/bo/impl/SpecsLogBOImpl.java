package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.ISpecsLogDAO;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.SpecsLog;
import com.bh.mall.exception.BizException;

@Component
public class SpecsLogBOImpl extends PaginableBOImpl<SpecsLog>
        implements ISpecsLogBO {

    @Autowired
    private ISpecsLogDAO specsLogDAO;

    @Override
    public void saveSpecsLog(String productCode, String productName,
            Specs specs, String type, Integer number, String updater) {
        SpecsLog data = new SpecsLog();
        String plCode = OrderNoGenerater
            .generate(EGeneratePrefix.ProductLog.getCode());
        data.setCode(plCode);
        data.setProductCode(productCode);
        data.setProductName(productName);
        data.setSpecsName(specs.getName());
        data.setSpecsCode(specs.getCode());

        data.setTranCount(number);
        data.setPreCount(specs.getStockNumber());
        data.setPostCount(specs.getStockNumber() + number);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());

        specsLogDAO.insert(data);
    }

    @Override
    public void removeSpecsLog(String code) {
        if (StringUtils.isNotBlank(code)) {
            SpecsLog data = new SpecsLog();
            data.setSpecsCode(code);
            specsLogDAO.delete(data);
        }
    }

    @Override
    public void refreshSpecsLog(SpecsLog data) {
        specsLogDAO.select(data);
    }

    @Override
    public List<SpecsLog> querySpecsLogList(SpecsLog condition) {
        return specsLogDAO.selectList(condition);
    }

    @Override
    public SpecsLog getSpecsLog(String code) {
        SpecsLog data = null;
        if (StringUtils.isNotBlank(code)) {
            SpecsLog condition = new SpecsLog();
            condition.setCode(code);
            data = specsLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "库存记录不存在");
            }
        }
        return data;
    }

}
