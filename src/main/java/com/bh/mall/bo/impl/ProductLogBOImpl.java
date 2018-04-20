package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IProductLogDAO;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductLog;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.exception.BizException;

@Component
public class ProductLogBOImpl extends PaginableBOImpl<ProductLog>
        implements IProductLogBO {

    @Autowired
    private IProductLogDAO productLogDAO;

    @Override
    public void saveProductLog(String code, String updater, String realNumber) {
        ProductLog data = new ProductLog();
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
        productLogDAO.insert(data);
    }

    @Override
    public void removeProductLog(String code) {
        if (StringUtils.isNotBlank(code)) {
            ProductLog data = new ProductLog();
            data.setProductCode(code);
            productLogDAO.delete(data);
        }
    }

    @Override
    public void refreshProductLog(ProductLog data) {
        productLogDAO.select(data);
    }

    @Override
    public List<ProductLog> queryProductLogList(ProductLog condition) {
        return productLogDAO.selectList(condition);
    }

    @Override
    public ProductLog getProductLog(String code) {
        ProductLog data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductLog condition = new ProductLog();
            condition.setCode(code);
            data = productLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "库存记录不存在");
            }
        }
        return data;
    }

    @Override
    public void saveChangeLog(Product data, String type, Integer realNumber,
            Integer changeNumber, String updater) {
        ProductLog plData = new ProductLog();
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
        productLogDAO.insert(plData);

    }

    @Override
    public void saveChangeProductLog(Product data, String type,
            Integer realNumber, Integer changeNumber, String approver) {
        ProductLog plData = new ProductLog();
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
        productLogDAO.insert(plData);

    }

}
