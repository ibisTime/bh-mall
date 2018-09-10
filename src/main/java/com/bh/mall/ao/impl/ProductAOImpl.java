package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.IInnerSpecsBO;
import com.bh.mall.bo.IProductBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISpecsBO;
import com.bh.mall.bo.ISpecsLogBO;
import com.bh.mall.bo.ITjAwardBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentPrice;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.Specs;
import com.bh.mall.domain.TjAward;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627540Req;
import com.bh.mall.dto.req.XN627541Req;
import com.bh.mall.dto.req.XN627543Req;
import com.bh.mall.dto.req.XN627546Req;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.exception.BizException;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    IProductBO productBO;

    @Autowired
    ISpecsBO specsBO;

    @Autowired
    IInnerSpecsBO innerSpecsBO;

    @Autowired
    IAgentPriceBO agentPriceBO;

    @Autowired
    ISpecsLogBO specsLogBO;

    @Autowired
    ITjAwardBO tjAwardBO;

    @Autowired
    IWareBO wareBO;

    @Autowired
    ISYSUserBO sysUserBO;

    @Override
    @Transactional
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

        // 只有一个规格时，数量必须为一
        boolean flag = true;
        for (XN627546Req specs : req.getSpecList()) {
            if (EBoolean.YES.getCode().equals(specs.getNumber())) {
                flag = false;
            }
        }
        if (flag) {
            throw new BizException("xn00000", "必须有一个的规格的数量为1");
        }

        specsBO.saveSpecsList(code, req.getName(), req.getSpecList(),
            req.getUpdater(), req.getRemark());
        tjAwardBO.saveTjAward(code, req.getAwardList());
        return code;
    }

    @Override
    @Transactional
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
        data.setRemark(req.getRemark());
        productBO.refreshProduct(data);

        List<XN627546Req> psList = req.getSpecList();

        Specs psCondition = new Specs();
        psCondition.setProductCode(data.getCode());
        List<Specs> dbPsList = specsBO.querySpecsList(psCondition);

        // 如果数据库未存在此规格，表示已经删除
        for (Specs specs : dbPsList) {
            boolean result = this.checkCode(specs.getCode(), psList);
            if (result) {
                specsBO.removeSpecs(specs);
            }
        }

        // 无code为新增，否则修改
        for (XN627546Req psReq : psList) {
            if (StringUtils.isBlank(psReq.getCode())) {
                specsBO.saveSpecs(data.getCode(), psReq);
            } else {
                specsBO.refreshSpecs(psReq, psReq.getSpecsPriceList());
            }
        }

        tjAwardBO.refreshTjAwardList(req.getAwardList());

    }

    @Override
    public Product getProduct(String code) {
        Product data = productBO.getProduct(code);
        Specs psCondition = new Specs();
        psCondition.setProductCode(data.getCode());

        List<Specs> psList = specsBO.querySpecsList(psCondition);
        // 推荐奖励
        TjAward aCondition = new TjAward();
        aCondition.setProductCode(data.getCode());
        List<TjAward> directAwardList = tjAwardBO.queryTjAwardList(aCondition);

        data.setDirectAwardList(directAwardList);
        if (CollectionUtils.isNotEmpty(psList)) {
            for (Specs productSpecs : psList) {

                AgentPrice pspCondition = new AgentPrice();
                pspCondition.setSpecsCode(productSpecs.getCode());

                List<AgentPrice> pspList = agentPriceBO
                    .queryAgentPriceList(pspCondition);

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
        specsBO.removeSpecsByProduct(code);
        specsLogBO.removeSpecsLog(code);
        tjAwardBO.removeTjAward(code);
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
    public Paginable<Product> selectProductPage(int start, int limit,
            Product condition) {

        Paginable<Product> page = productBO.getPaginable(start, limit,
            condition);
        List<Product> list = page.getList();
        for (Product product : list) {

            StringBuffer sb = new StringBuffer();
            // 产品规格
            Specs psCondition = new Specs();
            psCondition.setProductCode(product.getCode());
            List<Specs> psList = specsBO.querySpecsList(psCondition);
            for (Specs productSpecs : psList) {

                // 产品规格价格
                AgentPrice pspCondition = new AgentPrice();
                pspCondition.setSpecsCode(productSpecs.getCode());
                List<AgentPrice> pspList = agentPriceBO
                    .queryAgentPriceList(pspCondition);
                productSpecs.setPriceList(pspList);
                product.setSpecsList(psList);
                sb.append(productSpecs.getName() + ":["
                        + productSpecs.getStockNumber() + "]  ");
            }
            product.setShowNumber(sb.toString());

            // 获取各个代理云仓库存
            List<Ware> whList = wareBO.getWareByProductList(product.getCode());
            StringBuffer whNumber = new StringBuffer();
            for (Ware ware : whList) {
                whNumber.append(
                    ware.getSpecsQuantity() + "[" + ware.getSpecsName() + "],");
            }
            if (whNumber.lastIndexOf(",") > 0) {
                product.setWhNumber(
                    whNumber.substring(0, whNumber.lastIndexOf(",")));
            }

            // 奖励
            List<TjAward> awardList = tjAwardBO
                .getAwardByProduct(product.getCode());
            product.setDirectAwardList(awardList);
            //
            // SYSUser sysUser = sysUserBO.getSYSUser(product.getUpdater());
            // product.setUpdateName(sysUser.getRealName());
        }
        return page;
    }

    @Override
    public List<Product> selectProductList(Product condition) {
        List<Product> list = productBO.selectProductList(condition);
        for (Product product : list) {
            Specs psCondition = new Specs();
            psCondition.setProductCode(product.getCode());

            List<Specs> psList = specsBO.querySpecsList(psCondition);

            if (CollectionUtils.isNotEmpty(psList)) {
                for (Specs productSpecs : psList) {

                    AgentPrice pspCondition = new AgentPrice();
                    pspCondition.setSpecsCode(productSpecs.getCode());
                    List<AgentPrice> pspList = agentPriceBO
                        .queryAgentPriceList(pspCondition);
                    if (CollectionUtils.isNotEmpty(pspList)) {
                        productSpecs.setPriceList(pspList);
                    }
                }
                product.setSpecsList(psList);
            }
            // 奖励
            List<TjAward> awardList = tjAwardBO
                .getAwardByProduct(product.getCode());
            product.setDirectAwardList(awardList);
        }
        return list;
    }

    @Override
    public Product getProduct(String code, Integer level) {
        Product data = productBO.getProduct(code);
        List<Specs> specsList = specsBO.getSpecsByProduct(data.getCode());

        List<Specs> list = new ArrayList<Specs>();
        for (Specs productSpecs : specsList) {
            // 查询该等级能够看到的规格
            AgentPrice specsPrice = agentPriceBO
                .getPriceByLevel(productSpecs.getCode(), level);
            if (EBoolean.YES.getCode().equals(specsPrice.getIsBuy())
                    || 6 == level) {
                Specs ps = specsBO.getSpecs(specsPrice.getSpecsCode());
                ps.setPrice(specsPrice);
                list.add(ps);
            }
        }
        if (CollectionUtils.isEmpty(specsList)) {
            return null;
        }

        data.setSpecsList(list);
        return data;
    }

    private boolean checkCode(String code, List<XN627546Req> psList) {
        for (XN627546Req req : psList) {
            if (StringUtils.isNotBlank(req.getCode())
                    && req.getCode().equals(code)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Product getProductBySpecs(String specsCode, String level) {
        Specs specs = specsBO.getSpecs(specsCode);
        Product product = productBO.getProduct(specs.getProductCode());
        AgentPrice specsPrice = agentPriceBO.getPriceByLevel(specs.getCode(),
            StringValidater.toInteger(level));
        product.setSpecs(specs);
        product.setSpecsPrice(specsPrice);
        return product;
    }

    @Override
    public Paginable<Product> selectProductPageByB(int start, int limit,
            Product condition) {
        // 获取该等级代理可购买的规格
        long count = productBO.getTotalCountByB(condition);
        Page<Product> page = new Page<Product>(start, limit, count);
        List<Product> list = productBO.queryProductPage(page.getStart(),
            page.getPageSize(), condition);

        for (Product product : list) {

            // 产品规格
            Specs psCondition = new Specs();
            psCondition.setProductCode(product.getCode());
            psCondition.setIsBuy(EBoolean.YES.getCode());
            psCondition.setLevel(condition.getLevel());
            List<Specs> psList = specsBO.querySpecsListByB(psCondition);
            for (Specs productSpecs : psList) {

                // 产品规格价格
                AgentPrice pspCondition = new AgentPrice();
                pspCondition.setIsBuy(EBoolean.YES.getCode());
                pspCondition.setLevel(condition.getLevel());
                pspCondition.setSpecsCode(productSpecs.getCode());
                List<AgentPrice> pspList = agentPriceBO
                    .queryAgentPriceList(pspCondition);
                if (CollectionUtils.isNotEmpty(pspList)) {
                    productSpecs.setPriceList(pspList);
                }
            }
            product.setSpecsList(psList);
        }
        page.setList(list);
        return page;
    }

    @Override
    public Specs getProductByCustomer(String code) {
        Specs data = specsBO.getSpecs(code);
        AgentPrice price = agentPriceBO.getPriceByLevel(data.getCode(), 6);
        data.setAgentPrice(price.getPrice());
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        return data;
    }
}
