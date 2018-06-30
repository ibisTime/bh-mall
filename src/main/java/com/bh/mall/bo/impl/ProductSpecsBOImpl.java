package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IProductSpecsDAO;
import com.bh.mall.dao.IProductSpecsPriceDAO;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.dto.req.XN627547Req;
import com.bh.mall.exception.BizException;

@Component
public class ProductSpecsBOImpl extends PaginableBOImpl<ProductSpecs>
        implements IProductSpecsBO {

    @Autowired
    private IProductSpecsDAO productSpecsDAO;

    @Autowired
    private IProductSpecsPriceDAO productSpecsPriceDAO;

    @Autowired
    private IProductSpecsPriceBO productSpecsPriceBO;

    @Override
    public void saveProductSpecsList(String code, List<XN627546Req> specList) {

        // 添加产品规格
        for (XN627546Req productSpec : specList) {
            if (StringUtils.isBlank(productSpec.getCode())) {
                ProductSpecs data = new ProductSpecs();
                String psCode = OrderNoGenerater
                    .generate(EGeneratePrefix.ProductSpecs.getCode());
                data.setCode(psCode);
                data.setProductCode(code);
                data.setName(productSpec.getName());

                data.setNumber(
                    StringValidater.toInteger(productSpec.getNumber()));
                data.setWeight(
                    StringValidater.toDouble(productSpec.getWeight()));
                data.setIsUpgradeOrder(productSpec.getIsUpgradeOrder());
                data.setIsImpowerOrder(productSpec.getIsPowerOrder());
                data.setIsNormalOrder(productSpec.getIsNormalOrder());

                productSpecsDAO.insert(data);

                List<XN627547Req> specsPriceList = productSpec
                    .getSpecsPriceList();

                // 新增价格体系
                for (XN627547Req specsPrice : specsPriceList) {
                    ProductSpecsPrice productSpecsPrice = new ProductSpecsPrice();
                    String pspCode = OrderNoGenerater
                        .generate(EGeneratePrefix.ProductSpecsPrice.getCode());
                    productSpecsPrice.setCode(pspCode);
                    productSpecsPrice.setProductSpecsCode(psCode);
                    productSpecsPrice.setLevel(
                        StringValidater.toInteger(specsPrice.getLevel()));

                    productSpecsPrice.setMinNumber(
                        StringValidater.toInteger(specsPrice.getMinNumber()));
                    productSpecsPrice.setMinQuantity(
                        StringValidater.toInteger(specsPrice.getMinQuantity()));
                    productSpecsPrice.setPrice(
                        StringValidater.toLong(specsPrice.getPrice()));
                    productSpecsPrice.setChangePrice(
                        StringValidater.toLong(specsPrice.getChangePrice()));
                    productSpecsPrice.setIsBuy(specsPrice.getIsBuy());

                    productSpecsPrice.setDailyNumber(
                        StringValidater.toInteger(specsPrice.getDailyNumber()));
                    productSpecsPrice.setWeeklyNumber(StringValidater
                        .toInteger(specsPrice.getWeeklyNumber()));
                    productSpecsPrice.setMonthlyNumber(StringValidater
                        .toInteger(specsPrice.getMonthlyNumber()));
                    productSpecsPriceDAO.insert(productSpecsPrice);
                }
            }
        }

    }

    @Override
    public void removeProductSpecs(String productCode) {
        if (StringUtils.isNotBlank(productCode)) {
            ProductSpecs data = new ProductSpecs();
            data.setProductCode(productCode);

            ProductSpecsPrice pspData = new ProductSpecsPrice();
            pspData.setProductSpecsCode(productCode);
            productSpecsDAO.delete(data);
            productSpecsPriceDAO.delete(pspData);
        }
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition) {
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public ProductSpecs getProductSpecs(String code) {
        ProductSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductSpecs condition = new ProductSpecs();
            condition.setCode(code);
            data = productSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "产品规格不存在");
            }
        }
        return data;
    }

    @Override
    public void saveProductSpecs(String code, XN627546Req psReq) {
        ProductSpecs data = new ProductSpecs();
        String psCode = OrderNoGenerater
            .generate(EGeneratePrefix.ProductSpecs.getCode());
        data.setCode(psCode);
        data.setIsSingle(psReq.getIsSingle());
        data.setSingleNumber(
            StringValidater.toInteger(psReq.getSingleNumber()));
        data.setRefCode(psReq.getRefCode());

        data.setProductCode(code);
        data.setName(psReq.getName());
        data.setNumber(StringValidater.toInteger(psReq.getNumber()));
        data.setWeight(StringValidater.toDouble(psReq.getWeight()));
        data.setIsUpgradeOrder(psReq.getIsUpgradeOrder());

        data.setIsImpowerOrder(psReq.getIsPowerOrder());
        data.setIsNormalOrder(psReq.getIsNormalOrder());
        productSpecsDAO.insert(data);

        List<XN627547Req> specsPriceList = psReq.getSpecsPriceList();
        // 新增价格体系
        for (XN627547Req specsPrice : specsPriceList) {
            ProductSpecsPrice productSpecsPrice = new ProductSpecsPrice();
            String pspCode = OrderNoGenerater
                .generate(EGeneratePrefix.ProductSpecsPrice.getCode());
            productSpecsPrice.setCode(pspCode);
            productSpecsPrice.setProductSpecsCode(psCode);
            productSpecsPrice
                .setLevel(StringValidater.toInteger(specsPrice.getLevel()));

            productSpecsPrice
                .setPrice(StringValidater.toLong(specsPrice.getPrice()));
            productSpecsPrice.setMinNumber(
                StringValidater.toInteger(specsPrice.getMinNumber()));
            productSpecsPrice.setMinQuantity(
                StringValidater.toInteger(specsPrice.getMinQuantity()));

            productSpecsPrice.setChangePrice(
                StringValidater.toLong(specsPrice.getChangePrice()));
            productSpecsPrice.setIsBuy(specsPrice.getIsBuy());
            productSpecsPrice.setDailyNumber(
                StringValidater.toInteger(specsPrice.getDailyNumber()));
            productSpecsPrice.setWeeklyNumber(
                StringValidater.toInteger(specsPrice.getWeeklyNumber()));
            productSpecsPrice.setMonthlyNumber(
                StringValidater.toInteger(specsPrice.getMonthlyNumber()));
            productSpecsPriceDAO.insert(productSpecsPrice);
        }
    }

    @Override
    public List<ProductSpecs> getProductSpecsByProduct(String productCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setProductCode(productCode);
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public void refreshProductSpecs(XN627546Req psReq,
            List<XN627547Req> specsPriceList) {
        ProductSpecs psData = this.getProductSpecs(psReq.getCode());
        psData.setIsSingle(psReq.getIsSingle());
        psData.setSingleNumber(
            StringValidater.toInteger(psReq.getSingleNumber()));
        psData.setRefCode(psReq.getRefCode());

        psData.setName(psReq.getName());
        psData.setNumber(StringValidater.toInteger(psReq.getNumber()));
        psData.setWeight(StringValidater.toDouble(psReq.getWeight()));
        psData.setIsUpgradeOrder(psReq.getIsUpgradeOrder());

        psData.setIsImpowerOrder(psReq.getIsPowerOrder());
        psData.setIsNormalOrder(psReq.getIsNormalOrder());

        productSpecsDAO.update(psData);
        List<XN627547Req> pspList = specsPriceList;

        for (XN627547Req specsPrice : pspList) {
            ProductSpecsPrice pspData = productSpecsPriceBO
                .getProductSpecsPrice(specsPrice.getCode());
            pspData.setCode(specsPrice.getCode());
            pspData.setPrice(StringValidater.toLong(specsPrice.getPrice()));
            pspData.setChangePrice(
                StringValidater.toLong(specsPrice.getChangePrice()));
            pspData.setIsBuy(specsPrice.getIsBuy());

            pspData.setMinNumber(
                StringValidater.toInteger(specsPrice.getMinNumber()));
            pspData.setMinQuantity(
                StringValidater.toInteger(specsPrice.getMinQuantity()));

            pspData.setDailyNumber(
                StringValidater.toInteger(specsPrice.getDailyNumber()));
            pspData.setWeeklyNumber(
                StringValidater.toInteger(specsPrice.getWeeklyNumber()));
            pspData.setMonthlyNumber(
                StringValidater.toInteger(specsPrice.getMonthlyNumber()));
            productSpecsPriceDAO.update(pspData);
        }
    }

    @Override
    public Integer getMinSpecsNumber(String productCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setProductCode(productCode);
        List<ProductSpecs> list = productSpecsDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn00000", "该产品的规格不存在");
        }

        Integer number = 0;
        if (list.size() == 1) {
            ProductSpecs specs = list.get(0);
            return specs.getNumber();
        }
        for (ProductSpecs data : list) {
            number = data.getNumber();
            if (StringUtils.isNotBlank(data.getRefCode())) {
                ProductSpecs productSpecs = this
                    .getProductSpecs(data.getRefCode());
                number = getMinSpecsNumber(productSpecs, number);
            }
        }
        return number;
    }

    private Integer getMinSpecsNumber(ProductSpecs data, int number) {
        number = data.getNumber();
        if (StringUtils.isNotBlank(data.getRefCode())) {
            ProductSpecs productSpecs = this.getProductSpecs(data.getRefCode());
            number = number * productSpecs.getNumber();
            getMinSpecsNumber(data, number);
        }
        return number;
    }

}
