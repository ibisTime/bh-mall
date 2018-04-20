package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Award;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.dto.req.XN627540Req;
import com.bh.mall.dto.req.XN627541Req;
import com.bh.mall.dto.req.XN627543Req;
import com.bh.mall.dto.req.XN627545Req;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.enums.EAwardType;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.EProductNumberType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.exception.BizException;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IProductSpecsPriceBO productSpecsPriceBO;

    @Autowired
    private IProductLogBO productLogBO;

    @Autowired
    private IAwardBO awardBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addProduct(XN627540Req req) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PRODUCT.getCode());
        Product data = new Product();
        data.setCode(code);
        data.setName(req.getName());
        data.setAdPrice(StringValidater.toLong(req.getAdPrice()));
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setVirNumber(StringValidater.toInteger(req.getVirNumber()));

        data.setRealNumber(StringValidater.toInteger(req.getRealNumber()));
        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());
        data.setSlogan(req.getSlogan());
        data.setCreateDatetime(new Date());

        data.setStatus(EProductStatus.TO_Shelf.getCode());
        data.setIsTotal(req.getIsTotal());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        productBO.saveProduct(data);

        productSpecsBO.saveProductSpecsList(code, req.getSpecList());
        productLogBO.saveProductLog(code, req.getUpdater(),
            req.getRealNumber());
        awardBO.saveAward(code, req.getAwardList());
        return code;
    }

    @Override
    public void editProduct(XN627541Req req) {
        Product data = productBO.getProduct(req.getCode());
        if (EProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架，请下架后再修改");
        }
        data.setName(req.getName());
        data.setAdPrice(StringValidater.toLong(req.getAdPrice()));
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());

        data.setSlogan(req.getSlogan());
        data.setStatus(EProductStatus.TO_Shelf.getCode());
        data.setIsTotal(req.getIsTotal());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        productBO.refreshProduct(data);

        List<XN627546Req> psList = req.getSpecList();

        ProductSpecs psCondition = new ProductSpecs();
        psCondition.setProductCode(data.getCode());
        List<ProductSpecs> dbPsList = productSpecsBO
            .queryProductSpecsList(psCondition);

        for (ProductSpecs productSpecs : dbPsList) {
            if (psList.contains(productSpecs)) {
                productSpecsBO.removeProductSpecs(productSpecs.getCode());
            }
        }

        for (XN627546Req psReq : psList) {
            ProductSpecs psData = productSpecsBO
                .getProductSpecs(psReq.getCode());
            // 是否新增
            if (psData == null) {
                productSpecsBO.saveProductSpecs(data.getCode(), psReq);
            }
        }

        awardBO.refreshAwardList(req.getAwardList());

    }

    @Override
    public Product getProduct(String code) {
        Product data = productBO.getProduct(code);
        ProductSpecs psCondition = new ProductSpecs();
        psCondition.setProductCode(data.getCode());

        List<ProductSpecs> psList = productSpecsBO
            .queryProductSpecsList(psCondition);
        // 推荐奖励
        Award aCondition = new Award();
        aCondition.setType(EAwardType.DirectAward.getCode());
        aCondition.setProductCode(data.getCode());
        List<Award> directAwardList = awardBO.queryAwardList(aCondition);

        // 出货奖励
        aCondition.setType(EAwardType.SendAward.getCode());
        List<Award> sendAwardList = awardBO.queryAwardList(aCondition);
        data.setDirectAwardList(directAwardList);
        data.setSendAwardList(sendAwardList);
        if (CollectionUtils.isNotEmpty(psList)) {
            for (ProductSpecs productSpecs : psList) {

                ProductSpecsPrice pspCondition = new ProductSpecsPrice();
                pspCondition.setProductSpecsCode(productSpecs.getCode());

                List<ProductSpecsPrice> pspList = productSpecsPriceBO
                    .queryProductSpecsPriceList(pspCondition);

                if (CollectionUtils.isNotEmpty(pspList)) {
                    productSpecs.setPriceList(pspList);
                }
            }
            data.setSpecsList(psList);
        }

        return data;
    }

    @Override
    public void dropProduct(String code) {
        Product data = productBO.getProduct(code);
        if (EProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架,请下架后再删除");
        }
        productBO.removeProduct(data);
        // 删除规格、价格、库存记录
        productSpecsBO.removeProductSpecs(code);
        productLogBO.removeProductLog(code);
        awardBO.removeAward(code);
    }

    @Override
    public void putOnProduct(XN627543Req req) {
        Product data = productBO.getProduct(req.getCode());
        if (data.getStatus().equals(EProductStatus.Shelf_YES.getCode())) {
            throw new BizException("xn00000", "产品已上架");
        }
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        data.setIsFree(req.getIsFree());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setStatus(EProductStatus.Shelf_YES.getCode());
        productBO.putOnProduct(data);
    }

    @Override
    public void putdownProduct(String code, String updater) {
        Product data = productBO.getProduct(code);
        if (data.getStatus().equals(EProductStatus.Shelf_NO.getCode())
                || data.getStatus().equals(EProductStatus.TO_Shelf.getCode())) {
            throw new BizException("xn00000", "产品未上架");
        }
        productBO.putdownProduct(data, updater);
    }

    @Override
    public void editRepertory(XN627545Req req) {
        Product data = productBO.getProduct(req.getCode());
        Integer changeNumber = 0;
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        if (StringUtils.isNotBlank(req.getRealNumber())
                && StringValidater.toInteger(req.getRealNumber()) < 0) {
            throw new BizException("xn0000", "变动数量不能小于0");
        }
        if (StringUtils.isNotBlank(req.getVirNumber())
                && StringValidater.toInteger(req.getVirNumber()) < 0) {
            throw new BizException("xn0000", "变动数量不能小于0");
        }
        if (EProductNumberType.Real.getCode().equals(req.getKind())) {
            changeNumber = StringValidater.toInteger(req.getRealNumber());

            if (EProductLogType.Output.getCode().equals(req.getType())) {
                changeNumber = -changeNumber;
            }
            productLogBO.saveChangeLog(data, req.getType(),
                data.getRealNumber(), changeNumber, req.getUpdater());
            data.setRealNumber(data.getRealNumber() + changeNumber);
            productBO.refreshRealNumber(data);
        }
        if (EProductNumberType.Virtual.getCode().equals(req.getKind())) {
            changeNumber = StringValidater.toInteger(req.getVirNumber());
            if (EProductLogType.Output.getCode().equals(req.getType())) {
                changeNumber = -changeNumber;
            }
            // productLogBO.saveChangeLog(data, req.getType(),
            // data.getVirNumber(),
            // changeNumber, req.getUpdater());
            data.setVirNumber(data.getVirNumber() + changeNumber);
            productBO.refreshVirNumber(data);
        }

    }

    @Override
    public Paginable<Product> selectProductPage(int start, int limit,
            Product condition) {

        Paginable<Product> page = productBO.getPaginable(start, limit,
            condition);
        List<Product> list = page.getList();
        for (Product product : list) {

            ProductSpecs psCondition = new ProductSpecs();
            psCondition.setProductCode(product.getCode());

            List<ProductSpecs> psList = productSpecsBO
                .queryProductSpecsList(psCondition);
            // 推荐奖励
            Award aCondition = new Award();
            aCondition.setType(EAwardType.DirectAward.getCode());
            aCondition.setProductCode(product.getCode());
            List<Award> directAwardList = awardBO.queryAwardList(aCondition);

            // 出货奖励
            aCondition.setType(EAwardType.SendAward.getCode());
            List<Award> sendAwardList = awardBO.queryAwardList(aCondition);
            product.setDirectAwardList(directAwardList);
            product.setSendAwardList(sendAwardList);

            if (CollectionUtils.isNotEmpty(psList)) {
                for (ProductSpecs productSpecs : psList) {

                    ProductSpecsPrice pspCondition = new ProductSpecsPrice();
                    pspCondition.setProductSpecsCode(productSpecs.getCode());

                    List<ProductSpecsPrice> pspList = productSpecsPriceBO
                        .queryProductSpecsPriceList(pspCondition);
                    if (CollectionUtils.isNotEmpty(pspList)) {
                        productSpecs.setPriceList(pspList);
                    }
                }
                product.setSpecsList(psList);
            }
        }
        return page;
    }

    @Override
    public List<Product> selectProductList(Product condition) {
        List<Product> list = productBO.selectProductList(condition);
        for (Product product : list) {

            ProductSpecs psCondition = new ProductSpecs();
            psCondition.setProductCode(product.getCode());

            List<ProductSpecs> psList = productSpecsBO
                .queryProductSpecsList(psCondition);
            // 推荐奖励
            Award aCondition = new Award();
            aCondition.setType(EAwardType.DirectAward.getCode());
            aCondition.setProductCode(product.getCode());
            List<Award> directAwardList = awardBO.queryAwardList(aCondition);

            // 出货奖励
            aCondition.setType(EAwardType.SendAward.getCode());
            List<Award> sendAwardList = awardBO.queryAwardList(aCondition);
            product.setDirectAwardList(directAwardList);
            product.setSendAwardList(sendAwardList);

            if (CollectionUtils.isNotEmpty(psList)) {
                for (ProductSpecs productSpecs : psList) {

                    ProductSpecsPrice pspCondition = new ProductSpecsPrice();
                    pspCondition.setProductSpecsCode(productSpecs.getCode());
                    pspCondition.setLevel(condition.getLevel());
                    List<ProductSpecsPrice> pspList = productSpecsPriceBO
                        .queryProductSpecsPriceList(pspCondition);
                    if (CollectionUtils.isNotEmpty(pspList)) {
                        productSpecs.setPriceList(pspList);
                    }
                }
                product.setSpecsList(psList);
            }
        }
        return list;
    }

    @Override
    public Paginable<Product> selectProductByFrontPage(int start, int limit,
            Product condition) {

        Paginable<Product> page = productBO.getPaginable(start, limit,
            condition);
        List<Product> list = page.getList();
        for (Product product : list) {
            ProductSpecs psCondition = new ProductSpecs();
            psCondition.setProductCode(product.getCode());
            List<ProductSpecs> psList = productSpecsBO
                .queryProductSpecsList(psCondition);

            if (CollectionUtils.isNotEmpty(psList)) {
                for (ProductSpecs productSpecs : psList) {

                    ProductSpecsPrice pspCondition = new ProductSpecsPrice();
                    pspCondition.setProductSpecsCode(productSpecs.getCode());
                    pspCondition.setLevel(condition.getLevel());
                    List<ProductSpecsPrice> pspList = productSpecsPriceBO
                        .queryProductSpecsPriceList(pspCondition);
                    if (CollectionUtils.isNotEmpty(pspList)) {
                        productSpecs.setPriceList(pspList);
                    }
                }
                product.setSpecsList(psList);
            }
        }
        return page;
    }

    @Override
    public Product getProduct(String code, Integer level) {
        Product data = productBO.getProduct(code);
        List<ProductSpecs> list = productSpecsBO
            .getProductSpecsByProduct(data.getCode());
        for (ProductSpecs productSpecs : list) {
            ProductSpecsPrice condition = new ProductSpecsPrice();
            condition.setLevel(level);
            ProductSpecsPrice price = productSpecsPriceBO
                .getPriceByLevel(productSpecs.getCode(), level);
            productSpecs.setPrice(price);
        }
        data.setSpecsList(list);

        return data;
    }

}
