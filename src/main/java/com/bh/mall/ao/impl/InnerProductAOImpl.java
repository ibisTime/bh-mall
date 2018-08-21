package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.bo.IInnerProductBO;
import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627700Req;
import com.bh.mall.dto.req.XN627702Req;
import com.bh.mall.enums.EInnerProductStatus;
import com.bh.mall.enums.EInnerProductType;
import com.bh.mall.exception.BizException;

@Service
public class InnerProductAOImpl implements IInnerProductAO {

    @Autowired
    private IInnerProductBO innerProductBO;

    @Autowired
    private IInnerSpecsBO innerSpecsBO;

    @Override
    public String addInnerProduct(XN627700Req req) {
        InnerProduct data = new InnerProduct();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.InnerProduct.getCode());
        data.setCode(code);
        data.setName(req.getName());
        data.setSlogan(req.getSlogan());
        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());

        data.setStatus(EInnerProductStatus.TO_Shelf.getCode());
        data.setUpdater(req.getUpdater());
        data.setCreateDatetime(new Date());

        data.setRemark(req.getRemark());

        // 只有一个规格时，数量必须为一
        if (req.getSpecsList().size() == 1) {
            InnerSpecs specs = req.getSpecsList().get(0);
            if ("1".equals(specs.getNumber())) {
                throw new BizException("xn00000", "必须有一个的规格的数量为1");
            }
        }
        innerProductBO.saveInnerProduct(data);

        // 新增规格
        innerSpecsBO.saveInnerSpecs(code, req.getSpecsList());
        return code;
    }

    @Override
    public void editInnerProduct(XN627702Req req) {
        InnerProduct data = innerProductBO.getInnerProduct(req.getCode());
        if (EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架,请下架后再修改");
        }
        data.setName(req.getName());
        data.setSlogan(req.getSlogan());
        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());
        data.setPrice(StringValidater.toLong(req.getPrice()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        InnerSpecs psCondition = new InnerSpecs();
        psCondition.setInnerProductCode(data.getCode());
        List<InnerSpecs> dbPsList = innerSpecsBO
            .queryInnerSpecsList(psCondition);

        // 如果数据库未存在此规格，表示已经删除
        for (InnerSpecs innerSpecs : dbPsList) {
            boolean result = this.checkCode(innerSpecs.getCode(),
                req.getSpecsList());
            if (result) {
                innerSpecsBO.removeInnerSpecs(innerSpecs);
            }
        }

        // 无code为新增，否则修改
        for (InnerSpecs innerSpecs : req.getSpecsList()) {
            if (StringUtils.isBlank(innerSpecs.getCode())) {
                innerSpecsBO.saveInnerSpecs(data.getCode(), innerSpecs);
            } else {
                innerSpecsBO.refreshInnerSpecs(innerSpecs);
            }
        }
        innerProductBO.refreshInnerProduct(data);
    }

    @Override
    public void dropInnerProduct(String code) {
        InnerProduct data = innerProductBO.getInnerProduct(code);
        if (EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上架,请下架后再删除");
        }
        innerProductBO.removeInnerProduct(data);

        // 删除规格
        List<InnerSpecs> list = innerSpecsBO
            .getInnerSpecsByProduct(data.getCode());
        for (InnerSpecs innerSpecs : list) {
            innerSpecsBO.removeInnerSpecs(innerSpecs);

        }
    }

    @Override
    public Paginable<InnerProduct> queryInnerProductPage(int start, int limit,
            InnerProduct condition) {
        Paginable<InnerProduct> page = innerProductBO.getPaginable(start, limit,
            condition);
        for (InnerProduct data : page.getList()) {
            List<InnerSpecs> specsList = innerSpecsBO
                .getInnerSpecsByProduct(data.getCode());
            data.setSpecsList(specsList);
        }
        return page;
    }

    @Override
    public List<InnerProduct> queryInnerProductList(InnerProduct condition) {
        List<InnerProduct> list = innerProductBO
            .queryInnerProductList(condition);
        for (InnerProduct data : list) {
            List<InnerSpecs> specsList = innerSpecsBO
                .getInnerSpecsByProduct(data.getCode());
            data.setSpecsList(specsList);
        }
        return list;
    }

    @Override
    public InnerProduct getInnerProduct(String code) {
        InnerProduct data = innerProductBO.getInnerProduct(code);
        List<InnerSpecs> specsList = innerSpecsBO
            .getInnerSpecsByProduct(data.getCode());
        data.setSpecsList(specsList);
        return data;
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
            data.setQuantity(
                data.getQuantity() + StringValidater.toInteger(quantity));
        } else {
            data.setQuantity(
                data.getQuantity() - StringValidater.toInteger(quantity));
        }
        if (EInnerProductStatus.Shelf_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "请下架后再修改");
        }
        data.setUpdater(quantity);
        data.setUpdateDatetime(new Date());
        innerProductBO.changeQuantity(data);
    }

    private boolean checkCode(String code, List<InnerSpecs> psList) {
        for (InnerSpecs data : psList) {
            if (StringUtils.isNotBlank(code) && data.getCode().equals(code)) {
                return false;
            }
        }
        return true;
    }

}
