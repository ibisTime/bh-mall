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
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.ISpecsLogDAO;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.SpecsLog;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.exception.BizException;

@Component
public class SpecsLogBOImpl extends PaginableBOImpl<SpecsLog>
        implements ISpecsLogBO {

    @Autowired
    private ISpecsLogDAO specsLogDAO;

    @Override
    public void saveProductLog(String code, String updater, String realNumber) {
        SpecsLog data = new SpecsLog();
        String plCode = OrderNoGenerater
            .generate(EGeneratePrefix.ProductLog.getCode());
        data.setCode(plCode);
        data.setProductCode(code);
        data.setType(EProductLogType.Input.getCode());
        data.setTranCount(StringValidater.toInteger(realNumber));
        data.setPreCount(0);
        data.setPostCount(StringValidater.toInteger(realNumber));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        specsLogDAO.insert(data);
    }

    @Override
    public void removeProductLog(String code) {
        if (StringUtils.isNotBlank(code)) {
            SpecsLog data = new SpecsLog();
            data.setProductCode(code);
            specsLogDAO.delete(data);
        }
    }

    @Override
    public void refreshProductLog(SpecsLog data) {
        specsLogDAO.select(data);
    }

    @Override
    public List<SpecsLog> queryProductLogList(SpecsLog condition) {
        return specsLogDAO.selectList(condition);
    }

    @Override
    public SpecsLog getProductLog(String code) {
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

    @Override
    public void saveChangeLog(Product data, String type, Integer realNumber,
            Integer changeNumber, String updater) {
        SpecsLog plData = new SpecsLog();
        String plCode = OrderNoGenerater
            .generate(EGeneratePrefix.ProductLog.getCode());
        plData.setCode(plCode);
        plData.setType(type);
        plData.setProductCode(data.getCode());
        plData.setTranCount(changeNumber);
        plData.setPreCount(realNumber);
        plData.setPostCount(realNumber + changeNumber);
        plData.setUpdater(updater);
        plData.setUpdateDatetime(new Date());
        specsLogDAO.insert(plData);

    }

    @Override
    public void saveChangeProductLog(Product data, String type,
            Integer realNumber, Integer changeNumber, String approver) {
        SpecsLog plData = new SpecsLog();
        String plCode = OrderNoGenerater
            .generate(EGeneratePrefix.ProductLog.getCode());
        plData.setCode(plCode);
        plData.setType(type);
        plData.setProductCode(data.getCode());
        plData.setTranCount(changeNumber);
        plData.setPreCount(data.getRealNumber() + changeNumber);
        plData.setPostCount(data.getRealNumber());

        plData.setUpdater(approver);
        plData.setUpdateDatetime(new Date());
        specsLogDAO.insert(plData);

    }

}
