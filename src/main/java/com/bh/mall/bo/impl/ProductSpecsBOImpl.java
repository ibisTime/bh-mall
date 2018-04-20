package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IProductSpecsBO;
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
                    productSpecsPrice.setPrice(
                        StringValidater.toLong(specsPrice.getPrice()));
                    productSpecsPrice.setChangePrice(
                        StringValidater.toLong(specsPrice.getChangePrice()));
                    productSpecsPriceDAO.insert(productSpecsPrice);
                }
            }
        }

    }

    @Override
    public void refreshProductSpecs(List<XN627546Req> list) {

        for (XN627546Req req : list) {
            if (StringUtils.isNotBlank(req.getCode())) {
                ProductSpecs data = this.getProductSpecs(req.getCode());
                data.setName(req.getName());
                data.setNumber(StringValidater.toInteger(req.getNumber()));
                data.setWeight(StringValidater.toDouble(req.getWeight()));
                data.setIsUpgradeOrder(req.getIsUpgradeOrder());
                data.setIsImpowerOrder(req.getIsPowerOrder());
                data.setIsNormalOrder(req.getIsNormalOrder());
                productSpecsDAO.update(data);
                List<XN627547Req> specsPriceList = req.getSpecsPriceList();

                for (XN627547Req specsPrice : specsPriceList) {
                    ProductSpecsPrice pspData = new ProductSpecsPrice();
                    pspData.setCode(specsPrice.getCode());
                    pspData.setLevel(
                        StringValidater.toInteger(specsPrice.getLevel()));
                    pspData.setPrice(
                        StringValidater.toLong(specsPrice.getPrice()));
                    pspData.setChangePrice(
                        StringValidater.toLong(specsPrice.getChangePrice()));
                    productSpecsPriceDAO.update(pspData);
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
            productSpecsPrice.setChangePrice(
                StringValidater.toLong(specsPrice.getChangePrice()));
            productSpecsPriceDAO.insert(productSpecsPrice);
        }
    }

    @Override
    public List<ProductSpecs> getProductSpecsByProduct(String productCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setProductCode(productCode);
        return productSpecsDAO.selectList(condition);
    }

}
