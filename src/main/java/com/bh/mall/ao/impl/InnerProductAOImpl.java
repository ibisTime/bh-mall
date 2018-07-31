package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.bo.IInnerProductBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.dto.req.XN627700Req;
import com.bh.mall.dto.req.XN627701Req;
import com.bh.mall.enums.EInnerProductStatus;
import com.bh.mall.enums.EInnerProductType;
import com.bh.mall.exception.BizException;

@Service
public class InnerProductAOImpl implements IInnerProductAO {

    @Autowired
    private IInnerProductBO innerProductBO;

    @Override
    public String addInnerProduct(XN627700Req req) {
        InnerProduct data = new InnerProduct();
        String code = OrderNoGenerater.generate(EGeneratePrefix.InnerProduct
            .getCode());
        data.setCode(code);
        data.setProductName(req.getName());
        data.setSlogan(req.getSlogan());
        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());

        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        data.setStatus(EInnerProductStatus.TO_Shelf.getCode());
        data.setUpdater(req.getUpdater());
        data.setCreateDatetime(new Date());

        data.setRemark(req.getRemark());
        innerProductBO.saveInnerProduct(data);
        return code;
    }

    @Override
    public void editInnerProduct(XN627701Req req) {
        InnerProduct data = innerProductBO.getInnerProduct(req.getCode());
        if (EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架,请下架后再修改");
        }
        data.setProductName(req.getName());
        data.setSlogan(req.getSlogan());
        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());
        data.setPrice(StringValidater.toLong(req.getPrice()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        innerProductBO.refreshInnerProduct(data);
    }

    @Override
    public void dropInnerProduct(String code) {
        InnerProduct data = innerProductBO.getInnerProduct(code);
        if (EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架,请下架后再删除");
        }
        innerProductBO.removeInnerProduct(data);
    }

    @Override
    public Paginable<InnerProduct> queryInnerProductPage(int start, int limit,
            InnerProduct condition) {
        return innerProductBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<InnerProduct> queryInnerProductList(InnerProduct condition) {
        return innerProductBO.queryInnerProductList(condition);
    }

    @Override
    public InnerProduct getInnerProduct(String code) {
        return innerProductBO.getInnerProduct(code);
    }

    @Override
    public void putOnInnerProduct(String code, String orderNo, String isFree,
            String updater) {
        InnerProduct data = innerProductBO.getInnerProduct(code);
        if (EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架,请勿重复操作");
        }
        data.setOrderNo(StringValidater.toInteger(orderNo));
        data.setIsFree(isFree);
        data.setStatus(EInnerProductStatus.Shelf_YES.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        innerProductBO.putOnInnerProduct(data);
    }

    @Override
    public void putdownInnerProduct(String code, String updater) {
        InnerProduct data = innerProductBO.getInnerProduct(code);
        if (!EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品未处于上架状态");
        }
        data.setStatus(EInnerProductStatus.Shelf_NO.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        innerProductBO.putdownInnerProduct(data);
    }

    @Override
    public void changeQuantity(String code, String type, String quantity,
            String updater) {
        InnerProduct data = innerProductBO.getInnerProduct(code);
        if (EInnerProductType.ADD.getCode().equals(type)) {
            data.setQuantity(data.getQuantity()
                    + StringValidater.toInteger(quantity));
        } else {
            data.setQuantity(data.getQuantity()
                    - StringValidater.toInteger(quantity));
        }
        if (EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "请下架后再修改");
        }
        data.setUpdater(quantity);
        data.setUpdateDatetime(new Date());
        innerProductBO.changeQuantity(data);
    }
}
