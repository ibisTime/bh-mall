package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.ao.IUserAO;
import com.bh.mall.bo.IAwardBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.IProductLogBO;
import com.bh.mall.bo.IProductSpecsBO;
import com.bh.mall.bo.IProductSpecsPriceBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpecs;
import com.bh.mall.domain.ProductSpecsPrice;
import com.bh.mall.domain.User;
import com.bh.mall.dto.req.XN627540Req;
import com.bh.mall.dto.req.XN627541Req;
import com.bh.mall.dto.req.XN627545Req;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.enums.EProductLogType;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.enums.EProductType;
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
    private IUserAO userAO;

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

        productSpecsBO.saveProductSpecs(code, req.getSpecList(),
            req.getSpecsPriceList());
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
        data.setCreateDatetime(new Date());
        data.setStatus(EProductStatus.TO_Shelf.getCode());
        data.setIsTotal(req.getIsTotal());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        productBO.refreshProduct(data);

        // 是否新加了规格以及规格价格
        List<XN627546Req> psList = req.getSpecList();
        for (XN627546Req psReq : psList) {
            ProductSpecs psData = productSpecsBO
                .getProductSpecs(psReq.getCode());
            if (psData == null) {
                productSpecsBO.saveProductSpecs(data.getCode(),
                    req.getSpecList(), req.getSpecsPriceList());
            } else {
                productSpecsBO.refreshProductSpecs(req.getSpecList());
                productSpecsPriceBO
                    .refreshProductSpecsPrice(req.getSpecsPriceList());
            }
        }
        awardBO.refreshAwardList(req.getAwardList());

    }

    @Override
    public Product getProduct(String code) {
        Product data = productBO.getProduct(code);
        ProductSpecs condition = new ProductSpecs();
        List<ProductSpecs> psList = productSpecsBO
            .queryProductSpecsList(condition);

        if (psList.size() > 0) {
            data.setSpecsList(psList);
            for (ProductSpecs productSpecs : psList) {
                ProductSpecsPrice pspConditon = new ProductSpecsPrice();
                pspConditon.setProductSpecsCode(productSpecs.getCode());
                List<ProductSpecsPrice> pspList = productSpecsPriceBO
                    .queryProductSpecsPriceList(pspConditon);
                if (pspList.size() > 0) {
                    data.setPriceList(pspList);
                }
            }
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
    public void putonProduct(String code, String orderNo, String updater) {
        Product data = productBO.getProduct(code);
        if (data.getStatus().equals(EProductStatus.Shelf_YES.getCode())) {
            throw new BizException("xn00000", "产品已上架");
        }
        productBO.putonProduct(data, orderNo, updater);
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
        if (EProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架,请下架后再修改");
        }
        if (StringUtils.isNotBlank(req.getRealNumber()) && data
            .getRealNumber() < StringValidater.toInteger(req.getRealNumber())) {
            throw new BizException("xn0000", "变动数量大于剩余数量");
        }
        if (StringUtils.isNotBlank(req.getVirNumber()) && data
            .getVirNumber() < StringValidater.toInteger(req.getVirNumber())) {

        }
        if (EProductType.Real.getCode().equals(req.getKind())) {
            if (EProductLogType.Input.getCode().equals(req.getType())) {
                data.setRealNumber(data.getRealNumber()
                        + StringValidater.toInteger(req.getRealNumber()));
            } else {
                data.setRealNumber(data.getRealNumber()
                        - StringValidater.toInteger(req.getRealNumber()));
            }

        }
        if (EProductType.Virtual.getCode().equals(req.getKind())) {
            if (EProductLogType.Input.getCode().equals(req.getType())) {
                data.setVirNumber(data.getVirNumber()
                        + StringValidater.toInteger(req.getVirNumber()));
            } else {
                data.setVirNumber(data.getVirNumber()
                        - StringValidater.toInteger(req.getVirNumber()));
            }

        }
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        productBO.refreshRepertory(data);
        // 修改库存记录
        if (EProductType.Virtual.getCode().equals(req.getType())) {
            productLogBO.saveChangeLog(data, req.getType(),
                data.getRealNumber(), req.getRealNumber(), req.getUpdater());
        }

    }

    @Override
    public Paginable<Product> selectProductPage(int start, int limit,
            Product condition) {

        return productBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Product> selectProductList(Product condition) {
        return productBO.selectProductList(condition);
    }

    @Override
    public Paginable<Product> selectProductPageByFront(int start, int limit,
            Product condition) {
        if (StringUtils.isBlank(condition.getLevel())
                && StringUtils.isNotBlank(condition.getUserId())) {
            User data = userAO.doGetUser(condition.getUserId());
            condition.setLevel(data.getLevel());
        }

        long count = productBO.selectTotalCount(condition);
        Paginable<Product> page = new Page<Product>(start, limit, count);
        List<Product> list = productBO.selectProductPageByFront(condition,
            page.getStart(), page.getPageSize());
        page.setList(list);
        return page;
    }

}
