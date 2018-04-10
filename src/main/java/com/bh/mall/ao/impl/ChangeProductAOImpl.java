package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IChangeProductAO;
import com.bh.mall.bo.IChangeProductBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.User;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.enums.EChangeProductStatus;
import com.bh.mall.exception.BizException;

@Service
public class ChangeProductAOImpl implements IChangeProductAO {

    @Autowired
    private IChangeProductBO changeProductBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IWareHouseBO wareHouseBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IProductSpecsPriceBO productSpecsPriceBO;

    @Override
    public String addChangeProduct(XN627790Req req) {
        User uData = userBO.getUser(req.getApplyUser());
        WareHouse whData = wareHouseBO.getWareHouseByProductSpec(
            uData.getUserId(), req.getProductSpecsCode());
        if (whData.getQuantity() < StringValidater
            .toInteger(req.getQuantity())) {
            throw new BizException("xn000", "该规格的产品数量不足");
        }
        Product product = productBO.getProduct(req.getProductCode());
        ProductSpecs specs = productSpecsBO
            .getProductSpecs(req.getProductSpecsCode());
        ProductSpecsPrice specsPrice = productSpecsPriceBO
            .getPriceByLevel(specs.getCode(), uData.getLevel());

        Product changeProduct = productBO
            .getProduct(req.getChangeProductCode());
        ProductSpecs changeSpecs = productSpecsBO
            .getProductSpecs(req.getChangeSpecsCode());
        ProductSpecsPrice changeSpecsPrice = productSpecsPriceBO
            .getPriceByLevel(changeSpecs.getCode(), uData.getLevel());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ChangeProduct.getCode());
        ChangeProduct data = new ChangeProduct();
        data.setCode(code);
        data.setProductCode(req.getProductCode());
        data.setProductName(product.getName());
        data.setProductSpecsCode(req.getProductSpecsCode());
        data.setProductSpecsName(specs.getName());

        data.setPrice(specsPrice.getPrice());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        Long amount = AmountUtil.eraseLiUp(specsPrice.getPrice()
                * StringValidater.toInteger(req.getQuantity()));
        data.setAmount(amount);
        int canChangeQuantity = 0;
        if (changeSpecsPrice.getChangePrice() == null
                || changeSpecsPrice.getChangePrice() == 0) {
            throw new BizException("xn000", "该产品的换货价为空");
        } else {
            canChangeQuantity = (int) (amount
                    / changeSpecsPrice.getChangePrice());
        }

        data.setChangeProductCode(req.getChangeProductCode());
        data.setChangeProductName(changeProduct.getName());
        data.setChangeSpecsName(changeSpecs.getName());
        data.setChangeSpecsCode(req.getChangeProductCode());
        data.setCanChangeQuantity(canChangeQuantity);

        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());
        data.setApplyNote(req.getApplyNote());
        data.setStatus(EChangeProductStatus.TO_CHANGE.getCode());
        changeProductBO.saveChangeProduct(data);
        return code;

    }

    @Override
    public int editChangeProduct(ChangeProduct data) {
        return changeProductBO.refreshChangeProduct(data);

    }

    @Override
    public int dropChangeProduct(String code) {
        return changeProductBO.removeChangeProduct(code);

    }

    @Override
    public Paginable<ChangeProduct> queryChangeProductPage(int start, int limit,
            ChangeProduct condition) {
        return changeProductBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ChangeProduct> queryChangeProductList(ChangeProduct condition) {
        return changeProductBO.queryChangeProductList(condition);
    }

    @Override
    public ChangeProduct getChangeProduct(String code) {
        return changeProductBO.getChangeProduct(code);
    }
}
