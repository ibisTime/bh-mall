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
    public void saveProductSpecs(String code, List<XN627546Req> specList) {
        String psCode = null;
        String pspCode = null;

        // 添加产品规格
        for (XN627546Req productSpec : specList) {
            ProductSpecs data = new ProductSpecs();
            psCode = OrderNoGenerater
                .generate(EGeneratePrefix.ProductSpecs.getCode());
            data.setCode(psCode);
            data.setProductCode(code);
            data.setName(productSpec.getName());
            data.setNumber(StringValidater.toInteger(productSpec.getNumber()));
            data.setWeight(StringValidater.toInteger(productSpec.getWeight()));
            data.setIsUpgradeOrder(productSpec.getIsUpgradeOrder());

            data.setIsImpowerOrder(productSpec.getIsPowerOrder());
            data.setIsNormalOrder(productSpec.getIsNormalOrder());
            productSpecsDAO.insert(data);

            List<XN627547Req> specsPriceList = productSpec.getSpecsPriceList();

            // 新增价格体系
            for (XN627547Req specsPrice : specsPriceList) {
                ProductSpecsPrice pspData = new ProductSpecsPrice();

                pspCode = OrderNoGenerater
                    .generate(EGeneratePrefix.ProductSpecsPrice.getCode());
                pspData.setCode(pspCode);
                pspData.setProductSpecsCode(psCode);
                pspData
                    .setLevel(StringValidater.toInteger(specsPrice.getLevel()));
                pspData.setPrice(StringValidater.toLong(specsPrice.getPrice()));
                productSpecsPriceDAO.insert(pspData);
            }
        }

    }

    @Override
    public void refreshProductSpecs(List<XN627546Req> list) {

        for (XN627546Req req : list) {
            ProductSpecs data = new ProductSpecs();
            data.setCode(req.getCode());
            data.setName(req.getName());
            data.setNumber(StringValidater.toInteger(req.getNumber()));
            data.setWeight(StringValidater.toInteger(req.getWeight()));
            data.setIsUpgradeOrder(req.getIsUpgradeOrder());
            data.setIsImpowerOrder(req.getIsPowerOrder());
            data.setIsNormalOrder(req.getIsNormalOrder());
            productSpecsDAO.update(data);
            List<XN627547Req> specsPriceList = req.getSpecsPriceList();

            for (XN627547Req specsPrice : specsPriceList) {
                ProductSpecsPrice pspData = new ProductSpecsPrice();
                pspData.setCode(specsPrice.getCode());
                pspData
                    .setLevel(StringValidater.toInteger(specsPrice.getLevel()));
                pspData.setPrice(StringValidater.toLong(specsPrice.getPrice()));
                productSpecsPriceDAO.update(pspData);
            }
        }
    }

    @Override
    public void removeProductSpecs(String productCode) {
        if (StringUtils.isNotBlank(productCode)) {
            ProductSpecs data = new ProductSpecs();
            data.setProductCode(productCode);
            List<ProductSpecs> list = productSpecsDAO.selectList(data);
            if (list.size() > 0) {
                ProductSpecsPrice pspData = new ProductSpecsPrice();
                pspData.setProductSpecsCode(data.getCode());
                productSpecsDAO.delete(data);
                productSpecsPriceDAO.delete(pspData);
            }

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

}
